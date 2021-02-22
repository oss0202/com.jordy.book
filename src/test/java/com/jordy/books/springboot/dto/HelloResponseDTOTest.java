package com.jordy.books.springboot.dto;

import com.jordy.books.springboot.web.dto.HelloResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HelloResponseDTOTest {

    @Test
    public void 룸복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name); // 1), 2)
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
