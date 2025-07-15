package com.back.domain.coffee.entity;

import com.back.standard.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CoffeeItem extends BaseEntity {
    private final String coffeeName;
    private final int coffeePrice;
}
