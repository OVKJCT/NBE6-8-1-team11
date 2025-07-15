package com.back.domain.coffee.repository;

import com.back.domain.coffee.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Integer> {

}