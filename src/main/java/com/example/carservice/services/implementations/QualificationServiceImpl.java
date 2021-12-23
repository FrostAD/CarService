package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.repository.QualificationRepository;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import com.example.carservice.services.QualificationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class QualificationServiceImpl implements QualificationService {
    private final QualificationRepository qualificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public QualificationDTO getQualification(@Min(1) long id) {
        return modelMapper.map(qualificationRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Qualification invalid id")), QualificationDTO.class);
    }

    @Override
    public List<QualificationDTO> getQualifications() {
        return qualificationRepository.findAll().stream()
                .map(q -> modelMapper.map(q, QualificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void create(@Valid CreateQualificationDTO qualification) {
        qualificationRepository.save(modelMapper.map(qualification, Qualification.class));
    }

    @Override
    public Qualification updateQualification(long id, UpdateQualificationDTO qualificationDTO) {
        Qualification qualification = modelMapper.map(qualificationDTO, Qualification.class);
        qualification.setId(id);
        return qualificationRepository.save(qualification);
    }
    @Override
    public void deleteQualification(long id){
        qualificationRepository.deleteById(id);
    }

}
