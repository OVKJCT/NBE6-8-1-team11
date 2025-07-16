package com.back.domain.order.dto;

public record OrderDto (
        String orderId,
        int quantity,
        String email,
        String address,
        String zipCode
){
    public OrderDto(String orderId, int quantity, String email, String address, String zipCode) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.email = email;
        this.address = address;
        this.zipCode = zipCode;
    }
}