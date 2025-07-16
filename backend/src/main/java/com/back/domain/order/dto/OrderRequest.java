package com.back.domain.order.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private List<OrderItemDto> items;
    private String email;
    private String address;
    private String zipcode;

    @Getter
    @Setter
    public static class OrderItemDto {
        private Long productId;
        private int quantity;
    }
}