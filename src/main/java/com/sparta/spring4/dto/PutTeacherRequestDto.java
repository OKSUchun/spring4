package com.sparta.spring4.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class PutTeacherRequestDto {
    @Min(value = 0, message = "연차는 0이상이어야 합니다.")
    private Long expYear;
    private String phoneNo;

    private String company;

    private String introduction;
}