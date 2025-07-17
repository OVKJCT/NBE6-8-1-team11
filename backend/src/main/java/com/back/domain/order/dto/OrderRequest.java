package com.back.domain.order.dto;


import com.back.domain.order.entity.Order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    @Valid
    @NotEmpty(message = "주문 항목은 1개 이상이어야 합니다.")
    private List<OrderItemDto> items;
    @NotBlank(message = "이메일은 필수 입력 항목입니다.")
    private String email;
    private String address;
    private String zipCode;

    @Getter
    @Setter
    public static class OrderItemDto {
        private int productId;

        @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
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