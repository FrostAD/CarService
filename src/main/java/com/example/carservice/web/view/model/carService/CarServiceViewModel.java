package com.example.carservice.web.view.model.carService;

import com.example.carservice.data.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CarServiceViewModel {
    private long id;
    private String name;
    private int maxRepairingCars;

    private List<Qualification> serviceQualifications;

    private Set<Repair> vehiclesInRepair;

    private List<Brand> supportedBrands;

    private List<User> serviceEmployees;
    private Set<CarServicePricelist> pricelist;

}
