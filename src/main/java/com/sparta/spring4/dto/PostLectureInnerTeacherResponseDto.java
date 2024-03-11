package com.sparta.spring4.dto;

import com.sparta.spring4.entity.Teacher;
import lombok.Getter;

@Getter
public class PostLectureInnerTeacherResponseDto {
    private final Long teacherId;
    private final String teachername;

    private final Long expYear;
    private final String phoneNo;

    private final String company;
    private final String introduction;

    public PostLectureInnerTeacherResponseDto(Teacher teacher) {
        this.teacherId = teacher.getTeacherId();
        this.teachername = teacher.getTeachername();
        this.expYear = teacher.getExpYear();
        this.phoneNo = teacher.getPhoneNo();
        this.company = teacher.getCompany();
        this.introduction = teacher.getIntroduction();
    }
}
