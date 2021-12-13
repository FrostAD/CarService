package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Employee;
import com.example.carservice.dto.CreateEmployeeDTO;
import com.example.carservice.services.EmployeeService;
import com.example.carservice.web.view.model.employee.CreateEmployeeViewModel;
import com.example.carservice.web.view.model.employee.EmployeeViewModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    //TODO try with separate method convert to EmployeeViewModel
    @GetMapping("/")
    public String getEmployees(Model model){
        model.addAttribute("employees",employeeService.getEmployees().stream()
                .map(e -> modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList()));
        return "/employees/employees";
    }

    @GetMapping("/create-employee")
    public String showEmployeeCreateForm(Model model){
        model.addAttribute("employee",new CreateEmployeeViewModel());
        return "/employees/create-employee";
    }
    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute("employee") CreateEmployeeViewModel employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/employees/create-employee";
        }
        employeeService.create(modelMapper.map(employee, CreateEmployeeDTO.class));
        return "redirect:/employees";
    }

    //TODO make Qualification CRUD -> add list when creating employee(many to many)
    //TODO entity -> repository -> service,serviceImpl -> controller -> dto -> view-model -> templates

}
