package com.sparta.springhw3.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDetailForm {
    @NotNull
    private Long id;

    @NotNull
    @Min(value = 1, message = "주문 수량은 1개 이상, 100개 이하로 설정해주세요.")
    @Max(value = 100, message = "주문 수량은 1개 이상, 100개 이하로 설정해주세요.")
    private int quantity;
}
