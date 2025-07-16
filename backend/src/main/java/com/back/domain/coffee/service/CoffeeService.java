package com.back.domain.coffee.service;

import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.repository.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public long count() {
        return coffeeRepository.count();
    }

    public void addCoffee(String coffeeName, int coffeePrice) {
        if(coffeeRepository.findByCoffeeName(coffeeName).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 커피 상품입니다.");
        }

        coffeeRepository.save(new Coffee(coffeeName, coffeePrice));
    }

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }
}
