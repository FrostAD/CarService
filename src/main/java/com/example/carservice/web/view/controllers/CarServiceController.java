package com.example.carservice.web.view.controllers;

import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;
import com.example.carservice.services.BrandService;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.RepairService;
import com.example.carservice.web.view.model.brand.BrandViewModel;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.carService.CreateCarServiceViewModel;
import com.example.carservice.web.view.model.carService.UpdateCarServiceViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/carservices")
public class CarServiceController {
    private final CarServiceService carServiceService;
    private final BrandService brandService;
    private final RepairService repairService;
    private final ModelMapper modelMapper;


    @GetMapping("/")
    public String getCarServices(Model model){
        model.addAttribute("carServices",carServiceService.getCarServices().stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
        return "/carServices/carServices";
    }

    @GetMapping("/create-carservice")
    public String showCarServiceCreateForm(Model model){
        model.addAttribute("carService",new CreateCarServiceViewModel());
        model.addAttribute("brands",brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/carServices/create-carService";
    }
    @PostMapping("/create")
    public String createCarService(@Valid @ModelAttribute("carService") CreateCarServiceViewModel carService, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/carServices/create-carService";
        }
        carServiceService.createCarService(modelMapper.map(carService, CreateCarServiceDTO.class));

        return "redirect:/carservices/";
    }
    @GetMapping("/edit/{id}")
    public String showCarServiceEditForm(Model model, @PathVariable long id){
        UpdateCarServiceViewModel carServiceViewModel = modelMapper.map(carServiceService.getCarService(id), UpdateCarServiceViewModel.class);
        model.addAttribute("carService",carServiceViewModel);
        model.addAttribute("brands",brandService.getBrands().stream()
                .map(b -> modelMapper.map(b, BrandViewModel.class))
                .collect(Collectors.toList()));

        return "/carservices/edit-carService";
    }

    @PostMapping("/update/{id}")
    public String updateCarService(@Valid @ModelAttribute("carService") UpdateCarServiceViewModel carServiceViewModel,@PathVariable long id,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/carservices/edit-carService";
        }
        carServiceService.updateCarService(id,modelMapper.map(carServiceViewModel, UpdateCarServiceDTO.class));

        return "redirect:/carservices/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarService(@PathVariable long id){
        carServiceService.deleteCarService(id);
        return "redirect:/carservices/";
    }

    @GetMapping("/{id}/manage")
//    @PreAuthorize("isAuthenticated()")
    public String showManageCarService(@PathVariable long id,Model model){
        CarServiceViewModel carService = modelMapper.map(carServiceService.getCarService(id),CarServiceViewModel.class);
        model.addAttribute("id",id);
        System.out.println("SIZE");
        System.out.println(carService.getVehiclesInRepair().size());
        System.out.println(carService.getVehiclesInRepair());
        model.addAttribute("repairs",carService.getVehiclesInRepair());

        return "/carServices/manage";
    }

}
