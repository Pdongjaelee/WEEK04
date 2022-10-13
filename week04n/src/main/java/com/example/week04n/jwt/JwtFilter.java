package com.example.week04n.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilter {

    //provider 메소드를 필터에서 다시
    private final JwtProvider jwtProvider;

    public JwtFilter(JwtProvider jwtProvider)  {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. Request Header 에서 토큰을 꺼냄
        String jwt = jwtProvider.resolveToken((HttpServletRequest) request);
        if (jwt != null && jwtProvider.validateToken(jwt)) {   // token 검증
            Authentication auth = jwtProvider.getAuthentication(jwt);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
        }
        chain.doFilter(request, response);
    }
}
