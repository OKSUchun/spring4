package com.sparta.spring4.service;

import com.sparta.spring4.dto.GetLectureByCategoryResponseDto;
import com.sparta.spring4.dto.PostCommentRequestDto;
import com.sparta.spring4.dto.PostLectureRequestDto;
import com.sparta.spring4.dto.PostLectureResponseDto;
import com.sparta.spring4.entity.*;
import com.sparta.spring4.repository.CommentRepository;
import com.sparta.spring4.repository.LectureRepository;
import com.sparta.spring4.repository.LikeRepository;
import com.sparta.spring4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j(topic = "LectureService")
@Service
@RequiredArgsConstructor
public class LectureService {
    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;

    public PostLectureResponseDto createLecture(PostLectureRequestDto postLectureRequestDto) {
        // 강사 존재 여부 확인
        Teacher teacher = teacherRepository.findById(postLectureRequestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("해당 강사가 존재하지 않습니다."));

        // 강의명 중복확인
        checkDuplicateLectureName(postLectureRequestDto.getLectureName());

        Lecture newLecture = new Lecture(
                postLectureRequestDto.getLectureName(),
                postLectureRequestDto.getPrice(),
                postLectureRequestDto.getLectureIntroduction(),
                postLectureRequestDto.getCategoryEnum(),
                teacher
        );
        return new PostLectureResponseDto(lectureRepository.save(newLecture));

    }

    public List<GetLectureByCategoryResponseDto> getLectureByCategory(CategoryEnum category, SortByEnum sortBy, boolean isAsc) {
        // 오름차순 정렬
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        // 정렬 기준 설정
        String sortByStr = sortBy.getFieldName();
        Sort sort = Sort.by(direction, sortByStr);
        List<Lecture> lectureList = lectureRepository.findAllByCategory(category, sort);
        return lectureList.stream()
                .map(GetLectureByCategoryResponseDto::new)
                .toList();
    }

    public void createComment(Long lectureId, User user, PostCommentRequestDto postCommentRequestDto) {

        Lecture lecture = findLecture(lectureId);

        // 댓글 저장
        Comment comment = new Comment(postCommentRequestDto, lecture, user);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long lectureId, Long commentId, User user, PostCommentRequestDto postCommentRequestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        // 수정자와 작성자 일치 여부 확인
        validateUser(user, comment);

        // update
        comment.update(postCommentRequestDto);
    }


    public void deleteComment(Long lectureId, Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        validateUser(user, comment);

        commentRepository.delete(comment);
    }

    public void likeLecture(Long lectureId, User user) {
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        if (likeRepository.findByUserAndLecture(user, lecture).isPresent()) {
            // 이미 값이 존재하면 좋아요 취소
            Like like = likeRepository.findByUserAndLecture(user, lecture).get();
            likeRepository.delete(like);
        } else {
            // 좋아요 처리 한 적이 없다면 좋아요 처리
            likeRepository.save(new Like(user, lecture));
        }
    }

    private static void validateUser(User user, Comment comment) {
        if (!user.getUserId().equals(comment.getUser().getUserId())) {
            throw new SecurityException("작성자만 댓글을 수정/삭제할 수 있습니다.");
        }
    }


    // 강의명 중복 확인
    private void checkDuplicateLectureName(String lectureName) {
        if (lectureRepository.existsByLectureName(lectureName)) {
            throw new IllegalArgumentException("강의명이 중복입니다.");
        }
    }

    // 강의 확인
    private Lecture findLecture(Long lectureId) {
        return lectureRepository.findById(lectureId)
                .orElseThrow(() -> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));
    }
}
