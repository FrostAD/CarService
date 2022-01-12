package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.Vehicle;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.ReservationService;
import com.example.carservice.services.VehicleService;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.reservation.CreateReservationViewModel;
import com.example.carservice.web.view.model.vehicle.VehicleViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final CarServiceService carServiceService;
    private final VehicleService vehicleService;
    private final ModelMapper modelMapper;

    @GetMapping("/carservices/reserve/{id}")
    public String showAvailableForReservation(Model model, @PathVariable long id, Authentication authentication){
        VehicleDto vehicle = vehicleService.getCustomerVehicle(authentication, id);
//        List<CarServiceDTO> carServiceDTOList = carServiceService.getCarServicesByBrand(vehicle.getBrand());
//        System.out.println(ca);
        model.addAttribute("vehicle",vehicle);
        model.addAttribute("carServices",carServiceService.getCarServicesByBrand(vehicle.getBrand()).stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
        return "/customers/reservation-search-list";
    }
    @GetMapping("/carservices/{carServiceId}/reserve/{vehicleId}")
    public String showReservationCreateForm(Model model,@PathVariable("carServiceId") long carServiceId, @PathVariable("vehicleId") long vehicleId,Authentication authentication){
        //TODO check if every ID is correct (make checks for them)
        VehicleDto vehicleDto = vehicleService.getCustomerVehicle(authentication,vehicleId);
        model.addAttribute("vehicle",modelMapper.map(vehicleDto, VehicleViewModel.class));
        model.addAttribute("carService",carServiceService.getCarServicesByBrand(vehicleDto.getBrand()).stream()
                .filter(cs -> cs.getId() == carServiceId)
                .map(cs -> modelMapper.map(cs,CarServiceViewModel.class))
                .reduce((a,b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get());
        model.addAttribute("reservation",new CreateReservationViewModel());
        return "/customers/create-reservation";
    }
    @PostMapping("/carservices/reserve")
    public String createReservation(@Valid @ModelAttribute("reservation")CreateReservationViewModel createReservationViewModel, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(createReservationViewModel.getDate());
            System.out.println(bindingResult.getAllErrors());
            return "/customers/create-reservation";
        }
//        System.out.println(createReservationViewModel);
        reservationService.createReservation(modelMapper.map(createReservationViewModel, CreateReservationDTO.class));
        return "redirect:/vehicles/my";
    }

    @GetMapping("/reservations/{id}")
    public String viewVehicleReservations(@PathVariable("id") long vehicleId,Authentication authentication,Model model){
        VehicleDto vehicleDto = vehicleService.getCustomerVehicle(authentication,vehicleId);
        VehicleViewModel vehicleViewModel = modelMapper.map(vehicleDto,VehicleViewModel.class);
        System.out.println(vehicleViewModel.getReservations());
        model.addAttribute("reservations",modelMapper.map(vehicleDto,VehicleViewModel.class).getReservations());

        return "/vehicles/vehicle-reservations";
    }
}
