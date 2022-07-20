package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.OrderDetailRequestForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailRequestDto {
    private Long foodId;
    private int quantity;

    public OrderDetailRequestDto(OrderDetailRequestForm orderDetailForm) {
        this.foodId = orderDetailForm.getId();
        this.quantity = orderDetailForm.getQuantity();
    }
}
