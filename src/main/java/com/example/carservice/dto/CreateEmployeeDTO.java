package com.example.carservice.dto;

import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
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

//    private Set<Qualification> qualifications;
}
