package com.back.domain.coffee.entity;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class Coffee extends BaseEntity {
    @Column(unique = true)
    private String coffeeName;
    private int coffeePrice;

    protected Coffee() {
    }

    public Coffee(String coffeeName, int coffeePrice) {
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
    }
}