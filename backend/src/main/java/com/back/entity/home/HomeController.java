package com.back.entity.home;

import com.back.entity.item.entity.CoffeeNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String main() {
        return """
                <h1>API 서버</h1>
                <p>홈 페이지입니다.</p>
                <div>
                    <h2>API 문서</h2>
                    <h3>커피 종류:</h3>
                    <ul>
                        <li>%s</li>
                        <li>%s</li>
                        <li>%s</li>
                        <li>%s</li>
                    </ul>
                </div>
                """.formatted(
                CoffeeNames.COLUMBIA_NARINO,
                CoffeeNames.BRAZILL,
                CoffeeNames.COLUMBIA_QUINDIO,
                CoffeeNames.ETHIOPIA
        );
    }
}