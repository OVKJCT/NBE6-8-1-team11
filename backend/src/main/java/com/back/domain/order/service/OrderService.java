package com.back.domain.order.service;

import com.back.domain.order.entity.CoffeeOrder;
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

    public CoffeeOrder addOrder(CoffeeOrder order) {
        return orderRepository.save(order);
    }
}
