package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {
    //reg ID
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    private String model;
    private int productionYear;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    //TODO idk
//    @OneToMany(mappedBy = "vehicle")
//    private Set<Reservation> reservations = new HashSet<>();

    //TODO ONE TO MANY Set<Repairs>

}