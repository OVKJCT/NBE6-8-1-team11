package com.back.domain.order.entity;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class CoffeeOrder extends BaseEntity {
    private String coffeeId;
    private int quantity;

    @NotBlank
    private String email;
    private String address;
    private String zipCode;

    public CoffeeOrder(String orderId, int quantity, String email, String address, String zipCode) {
        this.coffeeId = orderId;
        this.quantity = quantity;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
    }
}