package com.sparta.springhw3.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FoodForm {

    @NotBlank(message = "메뉴 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "음식 가격을 입력해주세요.")
    @Min(value = 100, message = "음식 가격은 100원 이상, 1000000 이하로 설정해주세요.")
    @Max(value = 1000000, message = "음식 가격은 100원 이상, 1000000 이하로 설정해주세요.")
    private int price;
}
