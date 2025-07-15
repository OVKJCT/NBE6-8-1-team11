package com.back.domain.coffee.controller;

import com.back.domain.coffee.dto.CoffeeDto;
import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/coffee")
    public List<CoffeeDto> getCoffee() {
        List<Coffee> coffeeList = coffeeService.findAll();

        return coffeeList
                .stream()
                .map(CoffeeDto::new)
                .toList();
    }
}