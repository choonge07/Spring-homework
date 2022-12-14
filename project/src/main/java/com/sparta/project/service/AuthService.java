package com.sparta.project.service;

import com.sparta.project.dto.TokenDto;
import com.sparta.project.dto.TokenRequestDto;
import com.sparta.project.dto.UserRequestDto;
import com.sparta.project.dto.UserResponseDto;
import com.sparta.project.entity.User;
import com.sparta.project.jwt.RefreshToken;
import com.sparta.project.jwt.TokenProvider;
import com.sparta.project.repository.RefreshTokenRepository;
import com.sparta.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public UserResponseDto signup(UserRequestDto userRequestDto) {
//        if (userRepository.existByNickname(userRequestDto.getNickname())) {
//            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
//        }
        //닉네임, 비밀번호 검증
        Pattern namePattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,12}$");
        Matcher nameMatcher = namePattern.matcher(userRequestDto.getNickname());
        Pattern passPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z]).{4,32}$");
        Matcher passMatcher = passPattern.matcher(userRequestDto.getPassword());
        if (!nameMatcher.find()) {
            throw new IllegalArgumentException("nickname은 최소 4자 이상 12자 이하 알파벳 대소문자와 숫자로 구성해야합니다.");
        }
        if (!passMatcher.find()) {
            throw new IllegalArgumentException("password는 최소 4자 이상 32자 이하 알파벳 소문자와 숫자로 구성해야합니다.");
        }
        if (!userRequestDto.getPassword().equals(userRequestDto.getCheckPassword())) throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");

        User user = userRequestDto.toUser(passwordEncoder);
        return UserResponseDto.of(userRepository.save(user));
    }

    @Transactional
    public TokenDto login( UserRequestDto userRequestDto) {
        // 1. Login nickname/pw 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = userRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크)가 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue( TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 User ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 User ID를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresht Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 7. 토큰 발급
        return tokenDto;
    }
}
