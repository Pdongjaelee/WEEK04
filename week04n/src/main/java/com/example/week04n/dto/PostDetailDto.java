package com.example.week04n.dto;

import com.example.week04n.entity.Post;
//import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
//@Builder
public class PostDetailDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostDetailDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getUser().getUsername();
        this.createAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
