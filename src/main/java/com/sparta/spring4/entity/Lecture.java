package com.sparta.spring4.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lectures")
@NoArgsConstructor
public class Lecture extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false, unique = true)
    private String lectureName;

    @Column(nullable = false)
    private Long price;

    @Column
    private String lectureIntroduction;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryEnum category;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "lecture")
    private List<Comment> comments;

    @OneToMany(mappedBy = "lecture")
    private List<Like> likes;


    public Lecture(String lectureName, Long price, String lectureIntroduction, CategoryEnum category, Teacher teacher) {
        this.lectureName = lectureName;
        this.price = price;
        this.lectureIntroduction = lectureIntroduction;
        this.category = category;
        this.teacher = teacher;
    }

}
