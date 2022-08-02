package com.sparta.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String nickname;
    private String password;
    private String checkPassword;
}
