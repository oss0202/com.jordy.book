package com.jordy.books.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//아래의 import 3개는 직접 작성 8)
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jordy.books.springboot.web.HelloController;

@RunWith(SpringRunner.class) // 1)
@WebMvcTest(controllers = HelloController.class) // 2)
public class HelloControllerTest {
    @Autowired // 3)
    private MockMvc mvc; // 4)

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // 5)
                .andExpect(status().isOk()) // 6)
                .andExpect(content().string(hello)); // 7)
    }
}
