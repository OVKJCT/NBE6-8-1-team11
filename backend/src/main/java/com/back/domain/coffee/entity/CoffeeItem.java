package com.back.domain.coffee.entity;

import com.back.standard.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
public class CoffeeItem extends BaseEntity {
    @Column(unique = true)
    private String coffeeName;

    private int coffeePrice;
}
