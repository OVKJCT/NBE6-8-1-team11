package com.back.domain.order.entity;

import com.back.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "order"는 SQL 예약어일 수 있으므로 "orders"로 변경
@Getter
@Setter
@NoArgsConstructor
public class Order extends BaseEntity {
    @NotBlank
    private String email;
    private String address;
    private String zipCode;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    // 편의 메서드
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}