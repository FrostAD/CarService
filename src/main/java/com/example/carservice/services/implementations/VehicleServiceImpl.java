package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import com.example.carservice.data.repository.VehicleRepository;
import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import com.example.carservice.services.VehicleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<VehicleDto> getCustomerVehicles(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return vehicleRepository.getVehiclesByOwnerEquals(user.getId()).stream()
                .map(v -> modelMapper.map(v,VehicleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void createVehicle(@Valid CreateVehicleDTO vehicleDTO) {
        vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
    }
}
