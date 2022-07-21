package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.OrderDetailForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderDetailDto {

    @NoArgsConstructor
    @Getter
    public static class Request {
        private Long foodId;
        private int quantity;

        public Request(OrderDetailForm orderDetailForm) {
            this.foodId = orderDetailForm.getId();
            this.quantity = orderDetailForm.getQuantity();
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {
        private String name;
        private int quantity;
        private int price;
    }
}
