package com.example.week04n.sevice;

import com.example.week04n.dto.*;
import com.example.week04n.entity.Post;
import com.example.week04n.entity.User;
import com.example.week04n.repository.PostRepository;
import com.example.week04n.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
//@Builder
public class PostService {
    private final UserRepository userRepository;

    private final PostRepository postRepository;


    //username을 이용해서 User 객체 만들기 및 유저정보 확인
    private User getUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다"));
        return user;
    }

    //글 작성
    //@Transactional
    public PostResponseDto createPost(PostRequestDto postRequestDto, String username) {
        User user = getUser(username);

        Post post = new Post(postRequestDto, user);
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    //글 전체 조회
    public List<PostListResponseDto> getPosts() {
        List<Post> list = postRepository.findByOrderByModifiedAtDesc();
        List<PostListResponseDto> pList = new ArrayList<>();
        for (Post post : list) {
            PostListResponseDto listDto = PostListResponseDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .author(post.getUser().getUsername())
                    .createAt(post.getCreateAt())
                    .modifiedAt(post.getModifiedAt())
                    .build();
            pList.add(listDto);
        }
        return pList;
    }

    //글 하나만 조회
    public PostDetailDto getPostDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다"));
        return new PostDetailDto(post);
    }

    //글 수정
    public PostDetailDto updatePost(Long id, PostRequestDto requestDto, String username) {
        User user = getUser(username);
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        if (!post.getUser().getUsername().equals(user.getUsername()))
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
        post.update(requestDto);
        return new PostDetailDto(post);
    }

    //글 삭제
    public Long deletePost(Long id, String username) {
        User user = getUser(username);
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));
        if (!post.getUser().getUsername().equals(user.getUsername()))
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
        postRepository.deleteById(id);
        return id;
        }
    }




