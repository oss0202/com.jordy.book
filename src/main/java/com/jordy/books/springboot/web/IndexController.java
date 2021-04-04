package com.jordy.books.springboot.web;

import com.jordy.books.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    @GetMapping("/")
//    public String Index(){
//        return "index";
//    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){ // 1)
        model.addAttribute("posts",postsService.findAllDesc());
        return "index";
    }

}
