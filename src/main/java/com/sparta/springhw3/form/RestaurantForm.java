package com.sparta.springhw3.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class RestaurantForm {

    @NotBlank(message = "음식점 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "최소 주문 금액을 입력해주세요.")
    @Min(value = 1000, message = "최소 주문 금액은 1000원 이상, 100000 이하로 설정해주세요.")
    @Max(value = 100000, message = "최소 주문 금액은 1000원 이상, 100000 이하로 설정해주세요.")
    private int minOrderPrice;

    @NotNull(message = "배달료를 입력해주세요.")
    @Min(value = 0, message = "배달료는 0원 이상, 10000원 이하로 설정해주세요.")
    @Max(value = 10000, message = "최소 주문 금액은 0원 이상, 10000원 이하로 설정해주세요.")
    private int deliveryFee;
}
