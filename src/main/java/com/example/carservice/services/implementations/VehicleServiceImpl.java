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
        return vehicleRepository.getVehiclesByOwnerIdEquals(user.getId()).stream()
                .map(v -> modelMapper.map(v,VehicleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDto getCustomerVehicle(Authentication authentication, long id) {
        User user = (User) authentication.getPrincipal();
        System.out.println("Service USER");
        System.out.println(user);
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByOwnerIdEquals(user.getId()).stream().toList();
        Vehicle vehicle = (Vehicle) vehicleRepository.getVehiclesByOwnerIdEquals(user.getId()).stream()
                .filter(v -> v.getId() == id)
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                })
                .get();

//        Vehicle vehicle = vehicles.get(0);
        if(vehicle != null)
            return modelMapper.map(vehicle,VehicleDto.class);

        //TODO throw error or no access
        return null;
    }

    @Override
    public void createVehicle(@Valid CreateVehicleDTO vehicleDTO) {
        vehicleRepository.save(modelMapper.map(vehicleDTO, Vehicle.class));
    }
}
