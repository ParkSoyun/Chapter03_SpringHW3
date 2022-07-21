package com.sparta.springhw3.controller;

import com.sparta.springhw3.form.CustomCollectionValidator;
import com.sparta.springhw3.dto.FoodDto;
import com.sparta.springhw3.form.FoodForm;
import com.sparta.springhw3.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class FoodController {
    private final FoodService foodService;
    private final CustomCollectionValidator customCollectionValidator;

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody List<FoodForm> foodFormList, BindingResult bindingResult) throws BindException {
        customCollectionValidator.validate(foodFormList, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        List<FoodDto.Request> foodRequestDtoList = foodFormList.stream().map(FoodDto.Request::new).collect(Collectors.toList());

        foodService.registerFood(restaurantId, foodRequestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<FoodDto.Response> getMenuList(@PathVariable("restaurantId") Long restaurantId) {
        return foodService.getMenu(restaurantId);
    }
}
