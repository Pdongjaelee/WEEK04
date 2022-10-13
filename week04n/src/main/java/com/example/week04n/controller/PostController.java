package com.example.week04n.controller;


import ch.qos.logback.core.net.SMTPAppenderBase;
import com.example.week04n.dto.PostDetailDto;
import com.example.week04n.dto.PostListResponseDto;
import com.example.week04n.dto.PostRequestDto;
import com.example.week04n.dto.PostResponseDto;
import com.example.week04n.security.UserDetailImp;
import com.example.week04n.sevice.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    //글 작성//리스폰
    @PostMapping("api/auth/posts") //auth 붙여주는 이유는 다 인증과정(로그인)이 있어야 통과할 수 있고 security가 해줄거임
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailImp userDetail) {
        return postService.createPost(postRequestDto, userDetail.getUsername());
    }

    //글 전체 조회//리스트리스폰

    @GetMapping("api/posts")
    public List<PostListResponseDto> getPosts(){
        return postService.getPosts();
    }

    //글 하나만 조회
    @GetMapping("api/posts/{id}")
    public PostDetailDto getPostDetail(@PathVariable Long id) {
        return postService.getPostDetail(id);
    }


    // 글 수정
    @PutMapping("/api/auth/posts/{id}")
    public PostDetailDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailImp userDetail)  {
        return postService.updatePost(id, requestDto, userDetail.getUsername());
    }


    //글 삭제
    @DeleteMapping("/api/auth/posts/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailImp userDetail) {
        return postService.deletePost(id, userDetail.getUsername());
    }

}
