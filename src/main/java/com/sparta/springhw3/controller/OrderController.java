package com.sparta.springhw3.controller;

import com.sparta.springhw3.dto.OrderDto;
import com.sparta.springhw3.form.OrderForm;
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
    public OrderDto.Response orderFoods(@Valid @RequestBody OrderForm orderForm) {
        OrderDto.Request orderRequestDto = new OrderDto.Request(orderForm);

        return ordersService.orderFoods(orderRequestDto);
    }

    @GetMapping("/orders")
    public List<OrderDto.Response> getOrderList() {
        return ordersService.getOrderList();
    }
}
