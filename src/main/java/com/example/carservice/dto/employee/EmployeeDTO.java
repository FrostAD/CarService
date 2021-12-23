package com.example.carservice.dto.employee;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.QualificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String secondName;
    private List<Qualification> qualifications;
}
