package com.sparta.springhw3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailResponseDto {
    private String name;
    private int quantity;
    private int price;
}
