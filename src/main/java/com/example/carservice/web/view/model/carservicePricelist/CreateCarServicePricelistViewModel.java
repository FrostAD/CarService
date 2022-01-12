package com.example.carservice.web.view.model.carservicePricelist;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CreateCarServicePricelistViewModel {
    @NotNull
    private CarService carService;
    @NotNull
    private Qualification qualification;
    @NotNull
    //TODO make zero unacceptable
    private double price;
}
