package com.back.domain.coffee.entity;

import com.back.global.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Coffee extends BaseEntity {
    private final String coffeeName;
    private final int coffeePrice;
}
