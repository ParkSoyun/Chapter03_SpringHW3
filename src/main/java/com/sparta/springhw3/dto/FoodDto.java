package com.sparta.springhw3.dto;

import com.sparta.springhw3.domain.Restaurant;
import com.sparta.springhw3.form.FoodForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodDto {
    private Long id;
    private Restaurant restaurant;
    private String name;
    private int price;

    public FoodDto(FoodForm foodForm) {
        this.name = foodForm.getName();
        this.price = foodForm.getPrice();
    }
}
