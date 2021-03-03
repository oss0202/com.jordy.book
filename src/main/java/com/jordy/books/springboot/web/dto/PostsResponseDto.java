package com.jordy.books.springboot.web.dto;

import com.jordy.books.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String titile;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.titile = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }

}
