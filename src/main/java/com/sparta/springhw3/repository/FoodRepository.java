package com.sparta.springhw3.repository;

import com.sparta.springhw3.domain.Food;
import com.sparta.springhw3.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    boolean existsByRestaurantAndName(Restaurant restaurant, String name);
    List<Food> findAllByRestaurant(Restaurant restaurant);
}
