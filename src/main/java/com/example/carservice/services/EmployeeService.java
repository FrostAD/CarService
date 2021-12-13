package com.example.carservice.services;

import com.example.carservice.data.entity.Employee;
import com.example.carservice.dto.CreateEmployeeDTO;
import com.example.carservice.dto.EmployeeDTO;

import javax.validation.Valid;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getEmployees();
    void create(@Valid CreateEmployeeDTO employee);
}
