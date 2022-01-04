package com.example.carservice.dto.carService;

import com.example.carservice.data.entity.Brand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UpdateCarServiceDTO {
    @NotBlank
    private String name;
    @NotNull
    private int maxRepairingCars;

    private List<Brand> supportedBrands;
}
