package com.back.domain.order.controller;

import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.entity.Order;
import com.back.domain.order.entity.OrderItem;
import com.back.domain.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Tag(name = "OrderController", description = "API 주문 컨트롤러")
public class OrderController {
    private final OrderService orderService;

    //{
    //  "email": "test@naver.com",
    //  "address": "서울시 강남구 역삼동",
    //  "zipCode": "01234",
    //  "items": [
    //    { "productId": 1, "quantity": 2 },
    //    { "productId": 2, "quantity": 4 }
    //  ]
    //}
    @PostMapping("/orders")
    @Operation(summary = "주문 생성")
    public ResponseEntity<String> createOrder(@Validated @RequestBody OrderRequest orderRequest) {
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

        Order savedOrder = orderService.saveOrder(order);

        return ResponseEntity.ok("주문이 성공적으로 접수되었습니다. 주문 ID: " + savedOrder.getId());
    }
}
