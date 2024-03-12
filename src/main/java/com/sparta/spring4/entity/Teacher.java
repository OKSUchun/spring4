package com.sparta.spring4.entity;

import com.sparta.spring4.dto.PostTeacherRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teachers")
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column(nullable = false)
    private String teachername;
    @Column
    private Long expYear;
    @Column(nullable = false, unique = true)
    private String phoneNo;
    @Column
    private String company;
    @Column
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
}
