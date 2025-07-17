package com.back.domain.order.entity;

import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    @DisplayName("커피 주문 확인")
    void t2() throws Exception {
        OrderRequest.OrderItemDto item1 = new OrderRequest.OrderItemDto();
        item1.setProductId(1);
        item1.setQuantity(2);

        OrderRequest.OrderItemDto item2 = new OrderRequest.OrderItemDto();
        item2.setProductId(2);
        item2.setQuantity(1);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(item1, item2));
        orderRequest.setEmail("test@example.com");
        orderRequest.setAddress("서울특별시 테스트구 테스트로 123");
        orderRequest.setZipCode("01234");

        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증
        long count = orderService.count() + 1;
        mvc.perform(
                post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isOk())
                .andExpect(content().string("주문이 성공적으로 접수되었습니다. 주문 ID: " + count));// 주문 ID는 실제로는 동적으로 생성되므로, 테스트 환경에서는 고정된 값을 사용하거나 Mocking을 고려해야 합니다.;
    }
}