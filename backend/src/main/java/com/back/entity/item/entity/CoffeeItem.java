package com.back.entity.item.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoffeeItem {
    private final String coffeeName;
    private final int coffeePrice;
}
