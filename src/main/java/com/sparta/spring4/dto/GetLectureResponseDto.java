package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import lombok.Getter;

@Getter
public class GetLectureResponseDto {
    private Long lectureId;
    private String lectureName;
    private Long price;
    private String lectureIntroduction;
    private CategoryEnum category;
    private GetLectureInnerTeacherResponseDto teacher;

    public GetLectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.lectureIntroduction = lecture.getLectureIntroduction();
        this.category = lecture.getCategory();
        this.teacher = new GetLectureInnerTeacherResponseDto(lecture.getTeacher());
    }
}

