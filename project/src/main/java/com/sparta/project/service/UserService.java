package com.sparta.project.service;

import com.sparta.project.dto.SignupRequestDto;
import com.sparta.project.entity.User;
import com.sparta.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        // nickname 4자 이상 12자 이하 알파벳 대소문자 및 숫자로 구성 검증
        // password 4자 이상 32자 이하 알파벳 소문자 및 숫자로 구성 검증
        Pattern namePattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,12}$");
        Matcher nameMatcher = namePattern.matcher(requestDto.getNickname());
        Pattern passPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z]).{4,32}$");
        Matcher passMatcher = passPattern.matcher(requestDto.getPassword());
        if (!nameMatcher.find()) {
            throw new IllegalArgumentException("nickname은 최소 4자 이상 12자 이하 알파벳 대소문자와 숫자로 구성해야합니다.");
        }
        if (!passMatcher.find()) {
            throw new IllegalArgumentException("password는 최소 4자 이상 32자 이하 알파벳 소문자와 숫자로 구성해야합니다.");
        }
        String checkPassword = requestDto.getCheckPassword();
         //회원 ID 중복 확인
        Optional<User> found = userRepository.findByNickname(nickname);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        } else if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        password = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(nickname, password);
        userRepository.save(user);
    }
}

