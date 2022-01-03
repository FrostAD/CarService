package com.example.carservice.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
}