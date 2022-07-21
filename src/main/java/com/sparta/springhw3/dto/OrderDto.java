package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.OrderForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @NoArgsConstructor
    @Getter
    public static class Request {
        private Long restaurantId;
        private List<OrderDetailDto.Request> foods;

        public Request(OrderForm orderForm) {
            this.restaurantId = orderForm.getRestaurantId();
            this.foods = orderForm.getFoods().stream().map(OrderDetailDto.Request::new).collect((Collectors.toList()));
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private String restaurantName;
        private List<OrderDetailDto.Response> foods;
        private int deliveryFee;
        private int totalPrice;
    }
}
