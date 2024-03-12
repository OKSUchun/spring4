package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import lombok.Getter;

@Getter
public class PutLectureRequestDto {
    private String lectureName;
    private Long price;
    private String lectureIntroduction;
    private CategoryEnum categoryEnum;
}
