package com.sparta.spring4.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostLoginRequestDto {
    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    @Email
    private String username;
    @NotBlank(message = "패스워드는 반드시 입력해야 합니다.")
    private String password;
}