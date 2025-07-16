package com.back.domain.coffee.service;

import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public int findIdByName(String coffeeName) {
        return coffeeRepository.findByCoffeeName(coffeeName)
                .orElseThrow(() -> new IllegalArgumentException("해당 커피 상품이 존재하지 않습니다."))
                .getId();
    }
    public Coffee findById(int id) {
        return coffeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문 ID의 커피 상품이 존재하지 않습니다."));
    }

    public long count() {
        return coffeeRepository.count();
    }

    public void addCoffee(String coffeeName, int coffeePrice) {
        if(coffeeRepository.findByCoffeeName(coffeeName).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 커피 상품입니다.");
        }

        coffeeRepository.save(new Coffee(coffeeName, coffeePrice));
    }

    @Transactional(readOnly = true)
    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }
}
