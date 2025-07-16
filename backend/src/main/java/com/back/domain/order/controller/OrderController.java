package com.back.domain.order.controller;

import com.back.domain.order.dto.OrderDto;
import com.back.domain.order.entity.CoffeeOrder;
import com.back.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public OrderDto createOrder(@Validated @ModelAttribute("order") CoffeeOrder order) {
        CoffeeOrder save = orderService.addOrder(order);

        return new OrderDto(save.getCoffeeId(), save.getQuantity(), save.getEmail(), save.getAddress(), save.getZipCode());
    }
}
