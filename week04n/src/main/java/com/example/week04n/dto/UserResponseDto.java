package com.example.week04n.dto;

import com.example.week04n.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String username;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

public UserResponseDto(User user){
    this.id = user.getId();
    this.username = user.getUsername();
    this.createAt = user.getCreateAt();
    this.modifiedAt = user.getModifiedAt();
 }
}
