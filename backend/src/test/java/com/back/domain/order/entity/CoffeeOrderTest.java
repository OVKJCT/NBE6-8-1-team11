package com.back.domain.order.entity;

import com.back.domain.coffee.entity.Coffee;
import com.back.domain.coffee.service.CoffeeService;
import com.back.domain.order.dto.OrderRequest;
import com.back.domain.order.repository.OrderItemRepository;
import com.back.domain.order.repository.OrderRepository;
import com.back.domain.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
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

import static com.back.domain.order.dto.OrderRequest.OrderItemDto;
import static org.hamcrest.Matchers.hasSize;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EntityManager em;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderRequest orderRequest;

    @BeforeEach
    void setUp() {
        OrderItemDto item1 = new OrderItemDto();
        item1.setProductId(1);
        item1.setQuantity(2);

        OrderItemDto item2 = new OrderItemDto();
        item2.setProductId(2);
        item2.setQuantity(1);

        orderRequest = new OrderRequest();
        orderRequest.setItems(Arrays.asList(item1, item2));
        orderRequest.setEmail("test@example.com");
        orderRequest.setAddress("서울특별시 테스트구 테스트로 123");
        orderRequest.setZipCode("01234");
    }

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
        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증
        long count = orderService.count() + 1;
        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isOk())
                .andExpect(content().string("주문이 성공적으로 접수되었습니다. 주문 ID: " + count));// 주문 ID는 실제로는 동적으로 생성되므로, 테스트 환경에서는 고정된 값을 사용하거나 Mocking을 고려해야 합니다.;
    }

    @Test
    @DisplayName("이메일 미입력")
    void t3() throws Exception {
        orderRequest.setEmail(null);

        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증

        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isBadRequest()) // 잘못된 요청이므로 400 Bad Request 응답을 기대
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("email: 이메일은 필수 입력 항목입니다."));
    }

    @Test
    @DisplayName("주문 미입력")
    void t4() throws Exception {
        orderRequest.setItems(null);

        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증

        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isBadRequest()) // 잘못된 요청이므로 400 Bad Request 응답을 기대
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[0]").value("items: 주문 항목은 1개 이상이어야 합니다."));
    }

    @Test
    @DisplayName("주문, 이메일 미입력")
    void t5() throws Exception {
        orderRequest.setItems(null);
        orderRequest.setEmail(null);

        // DTO를 JSON 문자열로 변환
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        // 2. MockMvc를 사용하여 POST 요청 수행 및 결과 검증

        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)) // 요청 본문에 JSON 문자열을 포함
                .andExpect(status().isBadRequest()) // 잘못된 요청이므로 400 Bad Request 응답을 기대
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors", hasSize(2)));
    }

    @Test
    @DisplayName("수량을 0으로 입력")
    void t6() throws Exception {
        orderRequest.getItems().forEach(item -> item.setQuantity(0));

        String jsonRequest = objectMapper.writeValueAsString(orderRequest);

        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errors").isArray())
                .andExpect(jsonPath("$.errors[?(@ == 'items[0].quantity: 수량은 1 이상이어야 합니다.')]").exists())
                .andExpect(jsonPath("$.errors[?(@ == 'items[1].quantity: 수량은 1 이상이어야 합니다.')]").exists());
    }

    @Test
    @DisplayName("존재하지 않는 주문 ID 삭제")
    void t7() throws Exception {
        mvc.perform(delete("/api/orders/delete/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("해당 주문이 존재하지 않습니다."));
    }

    @Test
    @DisplayName("ID로 주문 삭제 성공")
    void t8() throws Exception {
        // given: 먼저 주문을 생성
        int id = 1;
        String jsonRequest = objectMapper.writeValueAsString(orderRequest);
        mvc.perform(
                        post("/api/orders/new")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest))
                .andExpect(status().isOk());

        em.flush();
        em.clear();

        ResultActions resultActions = mvc
                .perform(
                        delete("/api/orders/delete/%d".formatted(id))
                )
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(content().string("주문이 성공적으로 삭제되었습니다. 삭제 ID: " + id));

    }
}