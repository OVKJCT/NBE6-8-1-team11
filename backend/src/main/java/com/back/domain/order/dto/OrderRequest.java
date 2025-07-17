package com.back.domain.order.dto;

import com.back.domain.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private List<OrderItemDto> items;
    private String email;
    private String address;
    private String zipCode;

    @Getter
    @Setter
    public static class OrderItemDto {
        private int productId;
        private int quantity;
    }

    public OrderRequest(Order order) {
        this.email = order.getEmail();
        this.address = order.getAddress();
        this.zipCode = order.getZipCode();

        this.items = order.getOrderItems().stream()
                .map(orderItem -> {
                    OrderItemDto dto = new OrderItemDto();
                    dto.setProductId(orderItem.getProductId());
                    dto.setQuantity(orderItem.getQuantity());
                    return dto;
                })
                .toList();
    }
}