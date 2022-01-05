package com.example.carservice.web.view.model.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationViewModel {
    private long id;
    private CarService carService;
    private Vehicle vehicle;
    private LocalDate date;
    private boolean isComplete;
}