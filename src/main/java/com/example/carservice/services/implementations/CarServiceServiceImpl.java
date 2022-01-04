package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.repository.CarServiceRepository;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;
import com.example.carservice.services.CarServiceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class CarServiceServiceImpl implements CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createCarService(CreateCarServiceDTO carServiceDTO) {
        carServiceRepository.save(modelMapper.map(carServiceDTO, CarService.class));
    }

    @Override
    public List<CarServiceDTO> getCarServices() {
        return carServiceRepository.findAll().stream()
                .map(cs -> modelMapper.map(cs,CarServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarServiceDTO getCarService(@Min(1) long id) {
        return modelMapper.map(carServiceRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Brand id invalid")),CarServiceDTO.class);
    }

    @Override
    public void updateCarService(long id, UpdateCarServiceDTO carServiceDTO) {
        CarService carService = modelMapper.map(carServiceDTO,CarService.class);
        carService.setId(id);
        carServiceRepository.save(carService);
    }

    @Override
    public void deleteCarService(long id) {
        carServiceRepository.deleteById(id);
    }
}
