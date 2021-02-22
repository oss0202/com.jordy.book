package com.jordy.books.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //1)
@RequiredArgsConstructor // 2)
public class HelloResponseDTO {

    private final String name;
    private final int amount;

}
