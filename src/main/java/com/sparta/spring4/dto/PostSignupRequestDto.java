package com.sparta.spring4.dto;

import com.sparta.spring4.entity.UserRoleEnum;
import com.sparta.spring4.validation.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSignupRequestDto {
    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank
    private String username;

    @NotBlank(message = "패스워드는 반드시 입력해야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+{}\\[\\]:;<>,.?/~`-]).{8,15}$")
    private String password;

    private String gender;

    private String phone_num;

    private String address;

    @ValidEnum(enumClass = UserRoleEnum.class, message = "해당 권한은 존재하지 않습니다.")
    private UserRoleEnum role;

}
