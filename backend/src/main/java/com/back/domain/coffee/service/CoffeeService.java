package com.back.domain.coffee.service;

import com.back.domain.coffee.repository.CoffeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
}
