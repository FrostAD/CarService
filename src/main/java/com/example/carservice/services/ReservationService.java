package com.example.carservice.services;

import com.example.carservice.dto.reservation.CreateReservationDTO;

public interface ReservationService {
    void createReservation(CreateReservationDTO createReservationDTO);
}
