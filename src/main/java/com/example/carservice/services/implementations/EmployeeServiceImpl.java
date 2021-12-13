package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Employee;
import com.example.carservice.data.repository.EmployeeRepository;
import com.example.carservice.dto.CreateEmployeeDTO;
import com.example.carservice.dto.EmployeeDTO;
import com.example.carservice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public List<EmployeeDTO> getEmployees(){
        return employeeRepository.findAll().stream()
                .map(e -> modelMapper.map(e,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public void create(@Valid CreateEmployeeDTO employee){
        employeeRepository.save(modelMapper.map(employee,Employee.class));
    }

}
