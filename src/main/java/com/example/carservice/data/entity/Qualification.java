package com.example.carservice.data.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> qualifiedEmployees;

    @ManyToMany(mappedBy = "serviceQualifications")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<CarService> serviceQualifications;
}
