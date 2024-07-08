

package com.sparta.post.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private int price;
    private String username;

}