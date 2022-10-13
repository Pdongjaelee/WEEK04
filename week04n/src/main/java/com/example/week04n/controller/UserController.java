package com.example.week04n.controller;


import com.example.week04n.dto.UserRequestDto;
import com.example.week04n.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.week04n.sevice.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("api/user/signup")
    public UserResponseDto signup(@RequestBody @Valid UserRequestDto userRequestDto) throws IllegalAccessException {
        return userService.registerUser(userRequestDto);
    }


    //로그인
    @PostMapping("api/user/login")
    public UserResponseDto login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse httpServletResponse) {

        return userService.login(userRequestDto, httpServletResponse);
        //http 헤더에 토큰을 넣어 보내야하는데 들어가기 위한 바구니

    }
    //

}
