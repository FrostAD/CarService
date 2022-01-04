package com.example.carservice.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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
    @NotNull
    private int maxRepairingCars;

    @ManyToMany
    @JoinTable(
            name = "service_qualificaions",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private List<Qualification> serviceQualifications;

//    private List<Vehicle> or List<Repair> vehiclesInRepair;
    @OneToMany(mappedBy = "carService",fetch = FetchType.EAGER)
    @ToString.Exclude
//    @JsonManagedReference
    private Set<Repair> vehiclesInRepair = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "service_brands",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id")
    )
    private List<Brand> supportedBrands;

    @OneToMany(mappedBy = "carService")
    private List<User> serviceEmployees;
}
