package com.back.domain.order.controller;

import com.back.domain.order.dto.OrderDto;
import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.entity.CoffeeOrder;
import com.back.domain.order.entity.Order;
import com.back.domain.order.entity.OrderItem;
import com.back.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Tag(name = "OrderController", description = "API 주문 컨트롤러")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    @Transactional(readOnly = true)
    @Operation(summary = "주문 생성")
    public OrderDto createOrder(@Validated @ModelAttribute("order") CoffeeOrder order) {
        CoffeeOrder save = orderService.addOrder(order);

        return new OrderDto(save.getCoffeeId(), save.getQuantity(), save.getEmail(), save.getAddress(), save.getZipCode());
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        // DTO를 엔티티로 변환하는 과정
        Order order = new Order();
        order.setEmail(orderRequest.getEmail());
        order.setAddress(orderRequest.getAddress());
        order.setZipCode(orderRequest.getZipcode());

        List<OrderItem> orderItems = orderRequest.getItems().stream()
                .map(itemDto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductId(itemDto.getProductId());
                    orderItem.setQuantity(itemDto.getQuantity());
                    order.addOrderItem(orderItem); // Order에 OrderItem 추가
                    return orderItem;
                })
                .toList();

        return ResponseEntity.ok("주문이 성공적으로 접수되었습니다.");
    }
}
