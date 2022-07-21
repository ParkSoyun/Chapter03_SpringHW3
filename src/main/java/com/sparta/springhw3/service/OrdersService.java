package com.sparta.springhw3.service;

import com.sparta.springhw3.domain.Food;
import com.sparta.springhw3.domain.Orders;
import com.sparta.springhw3.domain.OrdersDetail;
import com.sparta.springhw3.domain.Restaurant;
import com.sparta.springhw3.dto.OrderDetailDto;
import com.sparta.springhw3.dto.OrderDto;
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

    private boolean checkMinOrderPrice(int minOrderPrice, int totalPrice) {
        return totalPrice >= minOrderPrice;
    }

    private OrderDto.Response makeOrderResponseDto(Orders order) {
        String restaurantName = order.getRestaurant().getName();

        List<OrdersDetail> ordersDetailList = ordersDetailRepository.findAllByOrder(order);

        List<OrderDetailDto.Response> foods = new ArrayList<>();

        for(OrdersDetail ordersDetail: ordersDetailList) {
            String name = ordersDetail.getFood().getName();
            int quantity = ordersDetail.getQuantity();
            int price = ordersDetail.getPrice();

            foods.add(new OrderDetailDto.Response(name, quantity, price));
        }

        int deliveryFee = order.getRestaurant().getDeliveryFee();
        int totalPrice = order.getTotalPrice();

        return new OrderDto.Response(restaurantName, foods, deliveryFee, totalPrice);
    }

    @Transactional
    public OrderDto.Response orderFoods(OrderDto.Request orderRequestDto) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        List<OrderDetailDto.Request> foods = orderRequestDto.getFoods();

        Restaurant restaurant = getRestaurant(restaurantId);

        Orders order = ordersRepository.save(new Orders(restaurant));

        List<OrdersDetail> ordersDetailList = new ArrayList<>();
        int totalPrice = 0;

        for(OrderDetailDto.Request orderDetailRequestDto: foods) {
            Long foodId = orderDetailRequestDto.getFoodId();
            int quantity = orderDetailRequestDto.getQuantity();

            Food food = getFood(foodId);
            int foodPrice = food.getPrice();

            int price = foodPrice * quantity;

            ordersDetailList.add(new OrdersDetail(order, food, quantity, price));

            totalPrice += price;
        }

        int minOrderPrice = restaurant.getMinOrderPrice();
        int deliveryFee = restaurant.getDeliveryFee();

        if(!checkMinOrderPrice(minOrderPrice, totalPrice)) {
            throw new IllegalArgumentException(minOrderPrice + "원부터 주문이 가능합니다.");
        }

        totalPrice += deliveryFee;

        ordersDetailRepository.saveAll(ordersDetailList);

        order.update(totalPrice);

        return makeOrderResponseDto(order);
    }

    public List<OrderDto.Response> getOrderList() {
        List<OrderDto.Response> orderResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = ordersRepository.findAll();

        for(Orders orders: ordersList) {
            OrderDto.Response orderResponseDto = makeOrderResponseDto(orders);

            orderResponseDtoList.add(orderResponseDto);
        }

        return orderResponseDtoList;
    }
}
