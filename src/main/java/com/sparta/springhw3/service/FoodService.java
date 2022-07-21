package com.sparta.springhw3.service;

import com.sparta.springhw3.domain.Food;
import com.sparta.springhw3.domain.Restaurant;
import com.sparta.springhw3.dto.FoodDto;
import com.sparta.springhw3.repository.FoodRepository;
import com.sparta.springhw3.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    static final int PRICE_UNIT = 100;

    private Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 음식점입니다."));
    }

    private boolean checkExistName(Restaurant restaurant, String name) {
        return foodRepository.existsByRestaurantAndName(restaurant, name);
    }

    private boolean checkPrice(int price) {
        return price % PRICE_UNIT == 0;
    }

    @Transactional
    public void registerFood(Long restaurantId, List<FoodDto.Request> foodRequestDtoList) {
        Restaurant restaurant = getRestaurant(restaurantId);

        for(FoodDto.Request foodRequestDto: foodRequestDtoList) {
            String name = foodRequestDto.getName();
            int price = foodRequestDto.getPrice();

            if(checkExistName(restaurant, name)) {
                throw new IllegalArgumentException("해당 이름의 메뉴가 이미 존재합니다.");
            }

            if(!checkPrice(price)) {
                throw new IllegalArgumentException("음식 가격은 100원 단위로만 입력이 가능합니다.");
            }

            Food food = new Food(restaurant, foodRequestDto);

            foodRepository.save(food);
        }
    }

    public List<FoodDto.Response> getMenu(Long restaurantId) {
        Restaurant restaurant = getRestaurant(restaurantId);

        List<Food> foodList = foodRepository.findAllByRestaurant(restaurant);
        List<FoodDto.Response> foodResponseDtoList = Arrays.asList(modelMapper.map(foodList, FoodDto.Response[].class));

        return foodResponseDtoList;
    }
}
