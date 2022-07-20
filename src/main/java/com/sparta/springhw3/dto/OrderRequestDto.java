package com.sparta.springhw3.dto;

import com.sparta.springhw3.form.OrderRequestForm;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderRequestDto {

    @NotNull
    private Long restaurantId;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<OrderDetailRequestDto> foods;

//    @Data
//    public static class Foods {
//        private Long foodId;
//        private int quantity;
//
//        public Foods(OrdersDetailForm.FoodsForm foodsForm) {
//            this.foodId = foodsForm.getFoodId();
//            this.quantity = foodsForm.getQuantity();
//        }
//    }

    public OrderRequestDto(OrderRequestForm ordersDetailForm) {
        this.restaurantId = ordersDetailForm.getRestaurantId();
        this.foods = ordersDetailForm.getFoods().stream().map(OrderDetailRequestDto::new).collect(Collectors.toList());
    }
}
