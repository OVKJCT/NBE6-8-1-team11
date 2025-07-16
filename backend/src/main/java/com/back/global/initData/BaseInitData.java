package com.back.global.initData;

import com.back.domain.coffee.service.CoffeeService;
import com.back.domain.order.entity.CoffeeOrder;
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

        if (orderService.count() == 0) {
            orderService.addOrder(new CoffeeOrder("1", 1, "asd@naver.com", "서울시 강남구 역삼동", "123-456"));
        }
    }
}