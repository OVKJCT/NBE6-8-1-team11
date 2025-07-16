package com.back.domain.coffee.controller;

import com.back.domain.coffee.dto.CoffeeDto;
import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Tag(name = "CoffeeController", description = "API 커피 컨트롤러")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @GetMapping("/coffees")
    @Transactional(readOnly = true)
    @Operation(summary = "커피 목록 조회")
    public List<CoffeeDto> getCoffee() {
        List<Coffee> coffeeList = coffeeService.findAll();

        return coffeeList
                .stream()
                .map(CoffeeDto::new)
                .toList();
    }
}