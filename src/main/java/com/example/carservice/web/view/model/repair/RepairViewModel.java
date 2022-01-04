package com.example.carservice.web.view.model.repair;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RepairViewModel {
    private long id;
    private CarService carService;
    private Vehicle vehicle;
    private Set<Qualification> plannedFixes;
    private User customer;
    private double sum;
    private boolean isComplete;
}
