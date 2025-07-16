package com.back.domain.coffee.dto;

import com.back.domain.coffee.entity.Coffee;

public record CoffeeDto(
        int id,
        String coffeeName,
        int coffeePrice
) {
    public CoffeeDto(Coffee coffee) {
        this(
                coffee.getId(),
                coffee.getCoffeeName(),
                coffee.getCoffeePrice()
        );
    }
}
