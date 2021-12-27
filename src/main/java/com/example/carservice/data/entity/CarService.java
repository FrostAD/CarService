package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "carService")
public class CarService extends BaseEntity{
    @NotBlank
    private String name;
    @NotBlank
    private int maxRepairingCars;

    @ManyToMany
    @JoinTable(
            name = "service_qualificaions",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private List<Qualification> serviceQualifications;

//    private List<Vehicle> vehiclesInRepair;

    @ManyToMany
    @JoinTable(
            name = "service_brands",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private List<Brand> supportedBrands;
}