package com.example.week04n.entity;


import com.example.week04n.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id라는 자동 증가되는 PK키

    @Id
    private Long id;

    @Column(nullable = false, unique = true) //(제약조건 DDL 생성 시 not null)
    private String username;

    @Column (nullable = false)
    private String password;


    public User(UserRequestDto userRequestDto) {
        this.username = userRequestDto.getUsername();
        this.password = userRequestDto.getPassword();
    }
}
