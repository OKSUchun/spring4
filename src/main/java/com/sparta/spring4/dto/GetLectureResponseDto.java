package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetLectureResponseDto {
    private Long lectureId;
    private String lectureName;
    private Long price;
    private String lectureIntroduction;
    private CategoryEnum category;
    private GetLectureInnerTeacherResponseDto teacher;
    private List<GetLectureInnerCommentResponseDto> comments;
    private Integer countLikes;

    public GetLectureResponseDto(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.lectureName = lecture.getLectureName();
        this.price = lecture.getPrice();
        this.lectureIntroduction = lecture.getLectureIntroduction();
        this.category = lecture.getCategory();
        this.teacher = new GetLectureInnerTeacherResponseDto(lecture.getTeacher());
        this.comments = lecture.getComments().stream()
                .map(GetLectureInnerCommentResponseDto::new)
                .collect(Collectors.toList());
        this.countLikes = lecture.getLikes().size();
    }
}

