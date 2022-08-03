package com.sparta.project.controller;
import com.sparta.project.dto.UserResponseDto;
import com.sparta.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<UserResponseDto> getUserInfo(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.getUserInfo(nickname));
    }
}
