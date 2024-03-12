package com.sparta.spring4.dto;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetLectureResponseDto {
    private final Long lectureId;
    private final String lectureName;
    private final Long price;
    private final String lectureIntroduction;
    private final CategoryEnum category;
    private final GetLectureInnerTeacherResponseDto teacher;
    private final List<GetLectureInnerCommentResponseDto> comments;
    private final Integer countLikes;

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

