package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import lombok.Getter;

@Getter
public class PostLectureResponseDto {
    private Long lectureId;
    private String lectureName;
    private Long price;
    private String lectureIntroduction;
    private CategoryEnum category;
    private PostLectureInnerTeacherResponseDto teacher;
    public PostLectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.lectureIntroduction = lecture.getLectureIntroduction();
        this.category = lecture.getCategory();
        this.teacher = new PostLectureInnerTeacherResponseDto(lecture.getTeacher());
    }
}
