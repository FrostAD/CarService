package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair,Long> {
}
