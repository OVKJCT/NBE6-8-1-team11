package com.back.domain.coffee.dto;

import com.back.domain.coffee.entity.Coffee;

public record CoffeeDto(
        String coffeeName,
        int coffeePrice
) {
    public CoffeeDto(Coffee coffee) {
        this(coffee.getCoffeeName(), coffee.getCoffeePrice());
    }
}
