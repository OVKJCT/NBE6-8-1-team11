package com.back.domain.order.service;

import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.entity.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(properties = {
        "scheduler.order.cron=*/3 * * * * *" //3초마다 실행
})
@Transactional
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        OrderRequest.OrderItemDto item1 = new OrderRequest.OrderItemDto();
        item1.setProductId(1);
        item1.setQuantity(2);

        OrderRequest.OrderItemDto item2 = new OrderRequest.OrderItemDto();
        item2.setProductId(2);
        item2.setQuantity(1);

        orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(item1, item2));
        orderRequest.setEmail("test@example.com");
        orderRequest.setAddress("서울특별시 테스트구 테스트로 123");
        orderRequest.setZipCode("01234");
    }

    @Test
    @DisplayName("주문 처리 테스트")
    void t1() throws Exception {
        Thread.sleep(4000);

        List<Order> orderList = orderService.findAll();
        assertTrue(orderList.isEmpty());
    }
}
