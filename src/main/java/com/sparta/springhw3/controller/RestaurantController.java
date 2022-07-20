package com.sparta.springhw3.controller;

import com.sparta.springhw3.dto.RestaurantDto;
import com.sparta.springhw3.form.RestaurantForm;
import com.sparta.springhw3.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping("/restaurant/register")
    public RestaurantDto.Response registerRestaurant(@Valid @RequestBody RestaurantForm restaurantForm, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        RestaurantDto.Request restaurantRequestDto = new RestaurantDto.Request(restaurantForm);

        return restaurantService.registerRestaurant(restaurantRequestDto);
    }

    @GetMapping("/restaurants")
    public List<RestaurantDto.Response> getRestaurantList() {
        return restaurantService.getRestaurantList();
    }
}
