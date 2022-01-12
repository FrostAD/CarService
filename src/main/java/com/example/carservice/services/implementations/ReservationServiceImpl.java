package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Reservation;
import com.example.carservice.data.repository.ReservationRepository;
import com.example.carservice.dto.reservation.CreateReservationDTO;
import com.example.carservice.services.ReservationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createReservation(CreateReservationDTO createReservationDTO) {
        System.out.println("DTOOO");
        System.out.println(createReservationDTO);
        System.out.println("ENTITY");
        System.out.println(modelMapper.map(createReservationDTO,Reservation.class));

        //TODO eventually
//        TypeMap<CreateReservationDTO, Reservation> propertyMapper = this.modelMapper.createTypeMap(CreateReservationDTO.class, Reservation.class);
//        propertyMapper.addMapping(CreateReservationDTO::getDate, Reservation::s);
        Reservation reservation = modelMapper.map(createReservationDTO,Reservation.class);
        reservation.getId().setDate(createReservationDTO.getDate());
        reservationRepository.save(reservation);
    }
}
