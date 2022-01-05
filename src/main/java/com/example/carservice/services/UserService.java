package com.example.carservice.services;

import com.example.carservice.data.entity.User;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createEmployee(CreateEmployeeDTO employee);
    void createCustomer(CreateCustomerDTO customer);
    List<EmployeeDTO> getEmployees();
}
