package com.example.carservice.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;

    @ManyToMany
    @JoinTable(
            name = "employee_qualifications",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "qualification_id")
    )
    private Set<Qualification> qualifications;
}
