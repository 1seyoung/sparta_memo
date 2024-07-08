package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private int price;
    private String username;


    public PostResponseDto(Post post) {
       this.id = post.getId();
       this.title = post.getTitle();
       this.content = post.getContent();
       this.username = post.getUsername();
       this.price = post.getPrice();
    }
}