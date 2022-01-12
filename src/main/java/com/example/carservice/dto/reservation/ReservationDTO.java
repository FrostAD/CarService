package com.example.carservice.dto.reservation;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Vehicle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDTO {
    private long id;
    private CarService carService;
    private Vehicle vehicle;
    private LocalDate date;
    private boolean isComplete;
}
