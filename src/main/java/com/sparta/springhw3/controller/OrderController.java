package com.sparta.springhw3.controller;

import com.sparta.springhw3.dto.OrderRequestDto;
import com.sparta.springhw3.dto.OrderResponseDto;
import com.sparta.springhw3.form.OrderRequestForm;
import com.sparta.springhw3.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrdersService ordersService;

    @PostMapping("/order/request")
    public OrderResponseDto orderFoods(@Valid @RequestBody OrderRequestForm ordersDetailForm) {
        OrderRequestDto orderRequestDto = new OrderRequestDto(ordersDetailForm);

        return ordersService.orderFoods(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrderList() {
        return ordersService.getOrderList();
    }
}
