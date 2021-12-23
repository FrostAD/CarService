package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.UpdateEmployeeDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.services.EmployeeService;
import com.example.carservice.services.QualificationService;
import com.example.carservice.web.view.model.employee.CreateEmployeeViewModel;
import com.example.carservice.web.view.model.employee.EmployeeViewModel;
import com.example.carservice.web.view.model.employee.UpdateEmployeeViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final QualificationService qualificationService;
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
        //double s
        model.addAttribute("qualificationss",qualificationService.getQualifications());
        return "/employees/create-employee";
    }
    @PostMapping("/create")
    public String createEmployee(@Valid @ModelAttribute("employee") CreateEmployeeViewModel employee, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/employees/create-employee";
        }
//        System.out.println(employee);
        employeeService.create(modelMapper.map(employee, CreateEmployeeDTO.class));
        return "redirect:/employees/";
    }
    @GetMapping("/edit/{id}")
    public String showEmployeeEditForm(Model model,@PathVariable long id){
        UpdateEmployeeViewModel employee = modelMapper.map(employeeService.getEmployee(id),UpdateEmployeeViewModel.class);
        model.addAttribute("employee",employee);
        model.addAttribute("qualificationsALL",qualificationService.getQualifications());
        return "/employees/edit-employee";
    }
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable long id,@Valid @ModelAttribute("employee") UpdateEmployeeViewModel employee,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/employees/edit-employee";
        }
        employeeService.updateEmployee(id,modelMapper.map(employee, UpdateEmployeeDTO.class));
        return "redirect:/employees/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return "redirect:/employees/";
    }

    //TODO make Qualification CRUD -> add list when creating employee(many to many)
    //TODO entity -> repository -> service,serviceImpl -> controller -> dto -> view-model -> templates

}
