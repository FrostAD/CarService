package com.example.carservice.services;

import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;

import javax.validation.constraints.Min;
import java.util.List;

public interface CarServiceService {
    void createCarService(CreateCarServiceDTO carServiceDTO);
    List<CarServiceDTO> getCarServices();
    CarServiceDTO getCarService(@Min(1) long id);
    void updateCarService(long id, UpdateCarServiceDTO carServiceDTO);
    void deleteCarService(long id);

}
