package com.back.domain.coffee.controller;

import com.back.domain.coffee.dto.CoffeeDto;
import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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

    @GetMapping("/coffees/{id}")
    @Transactional(readOnly = true)
    @Operation(summary = "커피 이미지 조회")
    public ResponseEntity<byte[]> getCoffeeImage(@PathVariable int id) throws IOException {
        ClassPathResource imgFile = new ClassPathResource("static/images/" + id + ".png");

        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }
}