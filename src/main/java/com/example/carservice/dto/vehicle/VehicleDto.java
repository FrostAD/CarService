package com.example.carservice.dto.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class VehicleDto {
    private long id;
    private Brand brand;
    private String model;
    private int productionYear;
    private User owner;
}
