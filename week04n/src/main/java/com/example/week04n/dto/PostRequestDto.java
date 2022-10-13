package com.example.week04n.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostRequestDto {

    private final String title;
    private final String contents;
}

