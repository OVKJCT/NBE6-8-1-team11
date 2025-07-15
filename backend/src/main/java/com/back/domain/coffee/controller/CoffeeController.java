package com.back.domain.coffee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CoffeeController {
    @GetMapping("/coffee")
    @ResponseBody
    public String getCoffee() {
        return "커피들";
    }
}