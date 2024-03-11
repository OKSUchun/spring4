package com.sparta.spring4.entity;

import com.sparta.spring4.dto.PostTeacherRequestDto;
import com.sparta.spring4.dto.PutTeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TEACHERS")
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false)
    private String teachername;
    private Long expYear;
    private String phoneNo;
    private String company;
    private String introduction;

    @OneToMany(mappedBy = "teacher")
    private List<Lecture> lectureList = new ArrayList<>();

    public Teacher(PostTeacherRequestDto postTeacherRequestDto) {
        this.teachername = postTeacherRequestDto.getTeachername();
        this.expYear = postTeacherRequestDto.getExpYear();
        this.phoneNo = postTeacherRequestDto.getPhoneNo();
        this.company = postTeacherRequestDto.getCompany();
        this.introduction = postTeacherRequestDto.getIntroduction();
    }

    public void update(PutTeacherRequestDto putTeacherRequestDto) {
        this.expYear = putTeacherRequestDto.getExpYear();
        this.phoneNo = putTeacherRequestDto.getPhoneNo();
        this.company = putTeacherRequestDto.getCompany();
        this.introduction = putTeacherRequestDto.getIntroduction();
    }
}
