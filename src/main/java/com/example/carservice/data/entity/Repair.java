package com.example.carservice.data.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "repair")
public class Repair extends BaseEntity{

    @ManyToOne
    @ToString.Exclude
//    @JsonBackReference
    private CarService carService;
    //vehicle's id
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    //List<>qualifications (какви услуги са ѝ направени - какво е направено на колата)
    @ManyToMany
    @JoinTable(
            name = "repair_services",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> plannedFixes;
    //user customerID
    @ManyToOne
    private User customer;
    //sum
    private double sum;
    //isComplete
    private boolean isComplete = false;
}
