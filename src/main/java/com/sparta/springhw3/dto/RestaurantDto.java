package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.RestaurantForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantDto {
    private Long id;
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    public RestaurantDto(RestaurantForm restaurantForm) {
        this.name = restaurantForm.getName();
        this.minOrderPrice = restaurantForm.getMinOrderPrice();
        this.deliveryFee = restaurantForm.getDeliveryFee();
    }
}
