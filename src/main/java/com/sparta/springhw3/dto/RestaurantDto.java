package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.RestaurantForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class RestaurantDto {

    @NoArgsConstructor
    @Getter
    public static class Request {
        private String name;
        private int minOrderPrice;
        private int deliveryFee;

        public Request(RestaurantForm restaurantForm) {
            this.name = restaurantForm.getName();
            this.minOrderPrice = restaurantForm.getMinOrderPrice();
            this.deliveryFee = restaurantForm.getDeliveryFee();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private Long id;
        private String name;
        private int minOrderPrice;
        private int deliveryFee;
    }
}
