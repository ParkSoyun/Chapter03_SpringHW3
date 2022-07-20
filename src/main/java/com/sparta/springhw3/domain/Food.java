package com.sparta.springhw3.domain;

import com.sparta.springhw3.dto.FoodDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String name;

    private int price;

    public Food(Restaurant restaurant, FoodDto foodDto) {
        this.restaurant = restaurant;
        this.name = foodDto.getName();
        this.price = foodDto.getPrice();
    }
}