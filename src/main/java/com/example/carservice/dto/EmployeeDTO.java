package com.example.carservice.dto;

import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String secondName;
//    private Set<Qualification> qualifications;
}
