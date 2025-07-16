package com.back.domain.order.entity;

import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CoffeeOrderTest {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("커피 종류 조회")
    void t1() throws Exception {
        ResultActions resultActions = mvc
                .perform(
                        get("/api/coffees")
                )
                .andDo(print());

        List<Coffee> coffeeList = coffeeService.findAll();

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(coffeeList.size()));
    }

    @Test
    @DisplayName("유효한 커피 주문 요청이 성공적으로 처리되어야 한다")
    void t2() throws Exception {
        // 1. 테스트할 요청 DTO 생성
        OrderRequest.OrderItemDto item1 = new OrderRequest.OrderItemDto();
        item1.setProductId(101L);
        item1.setQuantity(2);

        OrderRequest.OrderItemDto item2 = new OrderRequest.OrderItemDto();
        item2.setProductId(205L);
        item2.setQuantity(1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(item1, item2));
        orderRequest.setEmail("test@example.com");
        orderRequest.setAddress("서울특별시 테스트구 테스트로 123");
        orderRequest.setZipcode("01234");

        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증
        mvc.perform(post("/api/orders") // POST 요청을 /api/orders 엔드포인트로 보냄
                        .contentType(MediaType.APPLICATION_JSON) // 요청 본문의 Content-Type을 JSON으로 설정
                        .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isOk()) // HTTP 상태 코드가 200 OK인지 검증
                .andExpect(content().string("주문이 성공적으로 접수되었습니다.")); // 응답 본문이 예상과 일치하는지 검증
    }


}