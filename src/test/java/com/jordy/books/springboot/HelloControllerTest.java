package com.jordy.books.springboot;

import com.jordy.books.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//아래의 import 3개는 직접 작성 8)
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static  org.hamcrest.Matchers.is;

import com.jordy.books.springboot.web.HelloController;

@RunWith(SpringRunner.class) // 1)
@WebMvcTest(controllers = HelloController.class
    ,excludeFilters = {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE
        , classes = SecurityConfig.class)
}) // 2)
public class HelloControllerTest {
    @Autowired // 3)
    private MockMvc mvc; // 4)

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // 5)
                .andExpect(status().isOk()) // 6)
                .andExpect(content().string(hello)); // 7)
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getHello2() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello2"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                        .param("name", name) // 1)
                        .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name))) // 2)
                    .andExpect(jsonPath("$.amount", is(amount)));
    }
}
