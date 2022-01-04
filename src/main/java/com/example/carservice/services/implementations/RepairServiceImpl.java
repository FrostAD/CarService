package com.example.carservice.services.implementations;

import com.example.carservice.data.repository.RepairRepository;
import com.example.carservice.services.RepairService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {
    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;
}
