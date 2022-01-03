package com.example.carservice.web.view.model.vehicle;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.User;
import lombok.Data;

@Data
public class VehicleViewModel {
    private long id;
    private Brand brand;
    private String model;
    private int productionYear;
    private User owner;
}
