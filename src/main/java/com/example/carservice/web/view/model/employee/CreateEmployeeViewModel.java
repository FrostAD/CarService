package com.example.carservice.web.view.model.employee;


import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateEmployeeViewModel {
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;

//    private Set<Qualification> qualifications;
}
