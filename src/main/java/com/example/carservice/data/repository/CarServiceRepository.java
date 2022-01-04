package com.example.carservice.data.repository;

import com.example.carservice.data.entity.CarService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarServiceRepository extends JpaRepository<CarService,Long> {
}
