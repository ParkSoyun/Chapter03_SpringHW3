package com.sparta.springhw3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderResponseDto {
    private String restaurantName;
    private List<OrderDetailResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
