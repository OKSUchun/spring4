package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import lombok.Getter;

@Getter
public class GetLectureByCategoryResponseDto {
    private final Long lectureId;
    private final String lectureName;
    private final Long price;
    private final String lectureIntroduction;
    private final CategoryEnum category;
    public GetLectureByCategoryResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.lectureIntroduction = lecture.getLectureIntroduction();
        this.category = lecture.getCategory();
    }
}
