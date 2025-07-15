package com.back.domain.coffee.repository;

import com.back.domain.coffee.entity.CoffeeItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<CoffeeItem, Integer> {
}
