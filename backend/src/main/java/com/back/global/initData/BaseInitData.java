package com.back.global.initData;

import com.back.domain.coffee.service.CoffeeService;
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

    @Bean
    ApplicationRunner baseInitDataApplicationRunner() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        //초기화
        if(coffeeService.count() == 0) {
            coffeeService.addCoffee(COLUMBIA_NARINO, 2000);
            coffeeService.addCoffee(COLUMBIA_QUINDIO, 2500);
            coffeeService.addCoffee(ETHIOPIA, 3000);
            coffeeService.addCoffee(BRAZILL, 3500);
        }
    }
}