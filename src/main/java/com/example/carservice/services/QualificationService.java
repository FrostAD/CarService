package com.example.carservice.services;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface QualificationService {
    QualificationDTO getQualification(@Min(1) long id);
    List<QualificationDTO> getQualifications();
    void create(@Valid CreateQualificationDTO qualification);
    Qualification updateQualification(long id, UpdateQualificationDTO qualification);
    void deleteQualification(long id);
}
