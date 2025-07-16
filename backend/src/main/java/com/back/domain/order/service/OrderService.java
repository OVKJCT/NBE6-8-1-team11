package com.back.domain.order.service;

import com.back.domain.order.entity.Order;
import com.back.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    //매일 오루 2시에 주문 처리
    @Scheduled(cron = "0 0 14 * * *")
    public void processOrders() {
        LocalDateTime now = LocalDateTime.now();
        List<Order> orders = orderRepository.findByCreateDateBefore(now);

        for(Order o : orders) {
            //Order 삭제 코드
        }
    }
}
