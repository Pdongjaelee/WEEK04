package com.example.week04n.sevice;


import com.example.week04n.dto.TokenDto;
import com.example.week04n.dto.UserRequestDto;
import com.example.week04n.dto.UserResponseDto;
import com.example.week04n.entity.RefreshToken;
import com.example.week04n.entity.User;
import com.example.week04n.jwt.JwtProvider;
import com.example.week04n.repository.RefreshTokenRepositroy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.week04n.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepositroy refreshTokenRepositroy;
    private final JwtProvider jwtProvider;

    //회원가입
    public UserResponseDto registerUser(UserRequestDto userRequestDto) throws IllegalAccessException {
        String username = userRequestDto.getUsername();
        String password = userRequestDto.getPassword();
        String samepassword = userRequestDto.getSamepassword();
        String encodedPassword;

        Optional<User> findUserName = userRepository.findByUsername(username);
        if (findUserName.isPresent()) {
            throw new IllegalAccessException("중복된 닉네임이 있다");
        }
        if (!password.equals(samepassword)) {
            throw new IllegalAccessException("비밀번호가 서로 다릅니다!");

        }

        encodedPassword = passwordEncoder.encode(password);
        UserRequestDto dto = UserRequestDto.builder()
                .username(username)
                .password(encodedPassword)
                .samepassword(encodedPassword)
                .build();

        User user = new User(dto);
        userRepository.save(user);


        UserResponseDto responseDto = new UserResponseDto(user);

        return responseDto;

    }

    // 로그인
    public UserResponseDto login(UserRequestDto userRequestDto, HttpServletResponse httpServletResponse) {
        // 1. Login ID/PW 로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(), userRequestDto.getPassword());

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.createTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepositroy.save(refreshToken);

        httpServletResponse.addHeader("Access-Token",tokenDto.getGrantType()+" "+tokenDto.getAccessToken());
        httpServletResponse.addHeader("Refresh-Token", tokenDto.getRefreshToken());


        //userRequestDto.getUsername() 값과 동일한 userName의 모든 정보 불러오기
        //로그인 시 user 정보를 뿌려주고싶음
        User user = userRepository.findByUsername(userRequestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));


        UserResponseDto responseDto = new UserResponseDto(user);

        return responseDto;
    }


    //게시글 만들기

}



