package com.example.week04n.dto;

import com.example.week04n.entity.Post;
//import com.example.week04n.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private String title;
    private String contents;

    private Long id;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post){
        this.title = post.getTitle();
        this.contents = post.getContents();

        this.id = post.getId();
        this.author = post.getUser().getUsername();
        this.createAt = post.getCreateAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
