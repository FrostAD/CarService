package com.example.carservice.web.view.controllers;

import com.example.carservice.data.repository.UserRepository;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.services.UserService;
import com.example.carservice.web.view.model.customer.CreateCustomerViewModel;
import com.example.carservice.web.view.model.customer.CustomerViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/register")
    public String showCustomerCreateForm(Model model){
        model.addAttribute("customer", new CreateCustomerViewModel());
        return "/customers/create-customer";
    }
    @PostMapping("/register")
    public String createCustomer(@Valid @ModelAttribute("customer") CreateCustomerViewModel customerViewModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/customers/create-customer";
        }
        userService.createCustomer(modelMapper.map(customerViewModel, CreateCustomerDTO.class));
        return "redirect:/";
    }
}
