package com.example.carservice.dto.repair;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdateRepairDTO {
    @NotNull
    private CarService carService;
    @NotNull
    private Vehicle vehicle;
    private Set<Qualification> plannedFixes;
    @NotNull
    private User customer;
    private double sum;
    @NotNull
    private boolean isComplete;
}
