package com.sparta.springhw3.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestForm {

    private Long restaurantId;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderDetailRequestForm> foods;

}
