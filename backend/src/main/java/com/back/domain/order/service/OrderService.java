package com.back.domain.order.service;

import com.back.domain.order.entity.Order;
import com.back.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public long count() {
        return orderRepository.count();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    //주문 조회 메서드 추가 가능
}
