package com.example.carservice.web.view.model.reservation;

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
@ToString
public class ReservationViewModel {
    private long id;
    private long carServiceId;
    private String carServiceName;
    private long vehicleId;
    private LocalDate date;
    private boolean isComplete;
}
