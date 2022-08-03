package com.sparta.project.dto;

import com.sparta.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private String nickname;

    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getNickname());
    }
}
