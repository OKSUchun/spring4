package com.sparta.spring4.dto;

import com.sparta.spring4.entity.Comment;
import lombok.Getter;

@Getter
public class GetLectureInnerCommentResponseDto {
    private final Long commentId;
    private final String content;

    public GetLectureInnerCommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.content = comment.getContent();
    }
}
