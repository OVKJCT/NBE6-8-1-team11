package com.back.domain.coffee.repository;

import com.back.domain.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {
    Optional<Coffee> findByCoffeeName(String coffeeName);
}