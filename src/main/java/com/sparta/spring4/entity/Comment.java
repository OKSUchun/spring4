package com.sparta.spring4.entity;

import com.sparta.spring4.dto.PostCommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(PostCommentRequestDto postCommentRequestDto, Lecture lecture, User user) {
        this.content = postCommentRequestDto.getContent();
        this.lecture = lecture;
        this.user = user;
    }

    public void update(PostCommentRequestDto postCommentRequestDto) {
        this.content = postCommentRequestDto.getContent();
    }


}
