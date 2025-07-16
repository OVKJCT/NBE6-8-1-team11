package com.back.global.initData;

import com.back.domain.coffee.service.CoffeeService;
import com.back.domain.order.entity.Order;
import com.back.domain.order.entity.OrderItem;
import com.back.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import static com.back.domain.coffee.entity.CoffeeNames.*;

@Configuration
@RequiredArgsConstructor
public class BaseInitData {
    @Autowired
    @Lazy
    private BaseInitData self;

    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private OrderService orderService;

    @Bean
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        //초기화
        if (coffeeService.count() == 0) {
            coffeeService.addCoffee(COLUMBIA_NARINO, 2000);
            coffeeService.addCoffee(COLUMBIA_QUINDIO, 2500);
            coffeeService.addCoffee(ETHIOPIA, 3000);
            coffeeService.addCoffee(BRAZILL, 3500);
        }
    }

    @Transactional
    public void work2() {
        //초기화
        if (orderService.count() == 0) {
            Order order = new Order();
            order.setEmail("test@naver.com");
            order.setAddress("서울시 강남구 역삼동");
            order.setZipCode("01234");


            int id1 = coffeeService.findIdByName(COLUMBIA_NARINO);
            int id2 = coffeeService.findIdByName(COLUMBIA_QUINDIO);
            OrderItem orderItem1 = new OrderItem(id1, 2);
            OrderItem orderItem2 = new OrderItem(id2, 4);
            order.addOrderItem(orderItem1);
            order.addOrderItem(orderItem2);

            orderService.saveOrder(order);
        }
    }
}