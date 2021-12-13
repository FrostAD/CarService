package com.example.carservice.web.view.model.employee;

import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeViewModel {
    private String firstName;
    private String secondName;
//    private Set<Qualification> qualifications;
}
