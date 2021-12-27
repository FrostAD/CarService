package com.example.carservice.dto.employee;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.QualificationDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CreateEmployeeDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;

    private List<Qualification> qualifications;
}