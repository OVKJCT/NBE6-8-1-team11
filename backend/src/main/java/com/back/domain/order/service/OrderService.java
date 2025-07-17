package com.back.domain.order.service;

import com.back.domain.order.entity.Order;
import com.back.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }
    //매일 오후 2시에 주문 처리
    @Scheduled(cron = "${scheduler.order.cron}")
    public void processOrders() {
        LocalDateTime now = LocalDateTime.now();
        List<Order> orders = orderRepository.findByCreateDateBefore(now);

        //Order 삭제 코드
        orderRepository.deleteAll(orders);
    }
}
