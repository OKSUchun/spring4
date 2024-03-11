package com.sparta.spring4.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostTeacherRequestDto {
    @NotBlank(message = "강사 이름은 반드시 기입해야합니다.")
    private String teachername;

    @Min(value = 0, message = "연차는 0이상이어야 합니다.")
    private Long expYear;

    private String phoneNo;

    private String company;

    private String introduction;

}
