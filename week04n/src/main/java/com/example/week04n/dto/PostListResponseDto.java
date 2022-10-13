package com.example.week04n.dto;

import lombok.Builder;
//import com.example.week04n.entity.Post;;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
// 글목록가져오기위한 Dto
public class PostListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;


//    public PostListResponseDto(Post post){
//        this.id = post.getId();
//        this.title = post.getTitle();
//        this.author = post.getUser().getUsername();
//        this.createAt = post.getCreateAt();
//        this.modifiedAt = post.getModifiedAt();
//

}
