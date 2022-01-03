package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.User;
import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.services.BrandService;
import com.example.carservice.services.VehicleService;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.vehicle.CreateVehicleViewModel;
import com.example.carservice.web.view.model.vehicle.VehicleViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleService vehicleService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;


    //vehicles(customer)
    @GetMapping("/my")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getCustomerVehicles(Model model, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        System.out.println(user.getMyVehicles());
        model.addAttribute("vehicles",user.getMyVehicles().stream()
                .map(v -> modelMapper.map(v, VehicleViewModel.class))
                .collect(Collectors.toList()));

        return "/vehicles/my-vehicles";
    }
    @GetMapping("/create-vehicle")
    @PreAuthorize("isAuthenticated()")
    public String showVehicleCreateForm(Model model){
        model.addAttribute("vehicle",new CreateVehicleViewModel());
        model.addAttribute("brands",brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/vehicles/create-vehicle";
    }
    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public String createVehicle(@Valid @ModelAttribute("vehicle") CreateVehicleViewModel vehicle, BindingResult bindingResult,Authentication authentication){
        System.out.println(bindingResult.getAllErrors());
        if(bindingResult.hasErrors()){
            return "/vehicles/create-vehicle";
        }
        User user = (User) authentication.getPrincipal();
        vehicle.setOwner(user);

        vehicleService.createVehicle(modelMapper.map(vehicle, CreateVehicleDTO.class));

        return "redirect:/vehicles/my";
    }
}
