package com.sparta.springhw3.service;

import com.sparta.springhw3.domain.Food;
import com.sparta.springhw3.domain.Orders;
import com.sparta.springhw3.domain.OrdersDetail;
import com.sparta.springhw3.domain.Restaurant;
import com.sparta.springhw3.dto.*;
import com.sparta.springhw3.repository.FoodRepository;
import com.sparta.springhw3.repository.OrdersDetailRepository;
import com.sparta.springhw3.repository.OrdersRepository;
import com.sparta.springhw3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersDetailRepository ordersDetailRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    private Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
    }

    private Food getFood(Long foodId) {
        return foodRepository.findById(foodId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
    }

    private int checkMinOrderPrice(Restaurant restaurant, int totalPrice) {
        int minOrderPrice = restaurant.getMinOrderPrice();

        if(minOrderPrice > totalPrice) {
            return minOrderPrice;
        } else {
            return 0;
        }
    }

    private OrderResponseDto makeOrderResponseDto(Orders order) {
        String restaurantName = order.getRestaurant().getName();

        List<OrdersDetail> ordersDetailList = ordersDetailRepository.findAllByOrder(order);

        List<OrderDetailResponseDto> foods = new ArrayList<>();

        for(OrdersDetail ordersDetail: ordersDetailList) {
            String name = ordersDetail.getFood().getName();
            int quantity = ordersDetail.getQuantity();
            int price = ordersDetail.getPrice();

            foods.add(new OrderDetailResponseDto(name, quantity, price));
        }

        int deliveryFee = order.getRestaurant().getDeliveryFee();
        int totalPrice = order.getTotalPrice();

        return new OrderResponseDto(restaurantName, foods, deliveryFee, totalPrice);
    }

    @Transactional
    public OrderResponseDto orderFoods(OrderRequestDto orderRequestDto) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        List<OrderDetailRequestDto> foods = orderRequestDto.getFoods();

        Restaurant restaurant = getRestaurant(restaurantId);

        Orders insertOrder = new Orders(restaurant);

        Orders order = ordersRepository.save(insertOrder);
        int deliveryFee = restaurant.getDeliveryFee();

        int totalPrice = 0;
        List<OrdersDetail> ordersDetailList = new ArrayList<>();

        for(OrderDetailRequestDto orderDetailRequestDto: foods) {
            Long foodId = orderDetailRequestDto.getFoodId();
            int quantity = orderDetailRequestDto.getQuantity();

            Food food = getFood(foodId);
            int foodPrice = food.getPrice();

            int price = foodPrice * quantity;

            ordersDetailList.add(new OrdersDetail(order, food, quantity, price));

            totalPrice += price;
        }

        int minOrderPrice = checkMinOrderPrice(restaurant, totalPrice);

        if(minOrderPrice != 0) {
            throw new IllegalArgumentException(minOrderPrice + "원부터 주문이 가능합니다.");
        }

        totalPrice += deliveryFee;

        ordersDetailRepository.saveAll(ordersDetailList);

        order.update(totalPrice);

        return makeOrderResponseDto(order);
    }

    public List<OrderResponseDto> getOrderList() {
        List<OrderResponseDto> getResultDto = new ArrayList<>();

        List<Orders> ordersList = ordersRepository.findAll();

        for(Orders orders: ordersList) {
            OrderResponseDto orderResponseDto = makeOrderResponseDto(orders);

            getResultDto.add(orderResponseDto);
        }

        return getResultDto;
    }
}
