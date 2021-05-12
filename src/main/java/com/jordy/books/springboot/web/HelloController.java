package com.jordy.books.springboot.web;

import com.jordy.books.springboot.web.dto.HelloResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController// ①
public class HelloController {
    @GetMapping("/hello")// ②
    public String hello(){
        return "hello";
    }

    @GetMapping("hello/dto")
    public HelloResponseDTO helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){// 1)
        return new HelloResponseDTO(name, amount);

    }
}
