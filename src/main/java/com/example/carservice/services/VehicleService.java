package com.example.carservice.services;

import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.util.List;

public interface VehicleService {
    List<VehicleDto> getCustomerVehicles(Authentication authentication);

    void createVehicle(@Valid CreateVehicleDTO map);
}