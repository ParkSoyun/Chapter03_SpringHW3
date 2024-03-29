package com.sparta.springhw3.service;

import com.sparta.springhw3.domain.Restaurant;
import com.sparta.springhw3.dto.RestaurantDto;
import com.sparta.springhw3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    static final int MIN_ORDER_PRICE_UNIT= 100;
    static final int DELIVERY_FEE_UNIT = 500;

    private boolean checkMinOrderPrice(int minOrderPrice) {
        return minOrderPrice % MIN_ORDER_PRICE_UNIT == 0;
    }

    private boolean checkDeliveryFee(int deliveryFee) {
        return deliveryFee % DELIVERY_FEE_UNIT == 0;
    }

    public RestaurantDto.Response registerRestaurant(RestaurantDto.Request restaurantRequestDto) {
        int minOrderPrice = restaurantRequestDto.getMinOrderPrice();
        int deliveryFee = restaurantRequestDto.getDeliveryFee();

        if(!checkMinOrderPrice(minOrderPrice)) {
            throw new IllegalArgumentException("최소 주문 금액은 100원 단위로만 입력이 가능합니다.");
        }

        if(!checkDeliveryFee(deliveryFee)) {
            throw new IllegalArgumentException("배달료는 500원 단위로만 입력이 가능합니다.");
        }

        Restaurant restaurant = new Restaurant(restaurantRequestDto);

        Restaurant registerResult = restaurantRepository.save(restaurant);
        RestaurantDto.Response restaurantResponseDto = modelMapper.map(registerResult, RestaurantDto.Response.class);

        return restaurantResponseDto;
    }

    public List<RestaurantDto.Response> getRestaurantList() {
        List<Restaurant> restaurantList = restaurantRepository.findAll();

        List<RestaurantDto.Response> restaurantResponseDto = Arrays.asList(modelMapper.map(restaurantList, RestaurantDto.Response[].class));

        return restaurantResponseDto;
    }
}
