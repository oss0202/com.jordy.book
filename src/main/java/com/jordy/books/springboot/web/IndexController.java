package com.jordy.books.springboot.web;

import com.jordy.books.springboot.config.auth.LoginUser;
import com.jordy.books.springboot.config.auth.dto.SessionUser;
import com.jordy.books.springboot.service.posts.PostsService;
import com.jordy.books.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

//    @GetMapping("/")
//    public String Index(){
//        return "index";
//    }
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){  // 1)
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "index";
    }
//    Not Annotation
//    @GetMapping("/")
//    public String index(Model model){
//        model.addAttribute("posts", postsService.findAllDesc());
//
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); // 1)
//
//        if(user != null){ // 2)
//            model.addAttribute("userName", user.getName());
//        }
//        return "index";
//    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


//    @GetMapping("/")
//    public String index(Model model){ // 1)
//        model.addAttribute("posts",postsService.findAllDesc());
//        return "index";
//    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }


}
