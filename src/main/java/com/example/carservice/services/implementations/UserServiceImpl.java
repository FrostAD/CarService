package com.example.carservice.services.implementations;

import com.example.carservice.config.SecurityConfig;
import com.example.carservice.data.entity.Role;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.repository.RoleRepository;
import com.example.carservice.data.repository.UserRepository;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public void createEmployee(@Valid CreateEmployeeDTO employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        Role employeeRole = roleRepository.findByAuthority("EMPLOYEE");
        employee.setAuthorities(Arrays.asList(employeeRole));
        userRepository.save(modelMapper.map(employee,User.class));
    }

    @Override
    public void createCustomer(@Valid CreateCustomerDTO customer) {
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        Role customerRole = roleRepository.findByAuthority("CUSTOMER");
        customer.setAuthorities(Set.of(customerRole));
        userRepository.save(modelMapper.map(customer,User.class));
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        return userRepository.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
