package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.FoodForm;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class FoodDto {

    @NoArgsConstructor
    @Getter
    public static class Request {
        private String name;
        private int price;

        public Request(FoodForm foodForm) {
            this.name = foodForm.getName();
            this.price = foodForm.getPrice();
        }
    }

    @NoArgsConstructor
    @Getter
    public static class Response {
        private Long id;
        private String name;
        private int price;
    }
}
