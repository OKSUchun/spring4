package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostLectureRequestDto {

    @NotBlank(message = "강의명은 반드시 기입되어야 합니다.")
    private String lectureName;

    @Min(value = 0, message = "가격은 0원보다 큽니다.")
    private Long price;

    private String lectureIntroduction;

    private CategoryEnum categoryEnum;

    private Long teacherId;

}
