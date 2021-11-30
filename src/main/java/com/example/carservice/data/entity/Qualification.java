package com.example.carservice.data.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "qualification")
public class Qualification extends BaseEntity {
    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "qualifications")
    private Set<Employee> qualifiedEmployees;
}
