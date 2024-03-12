package com.sparta.spring4.controller;

import com.sparta.spring4.dto.*;
import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.SortByEnum;
import com.sparta.spring4.entity.User;
import com.sparta.spring4.entity.UserRoleEnum;
import com.sparta.spring4.security.UserDetailsImpl;
import com.sparta.spring4.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/lectures")
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("")
    public ResponseEntity<PostLectureResponseDto> createLecture(@Valid @RequestBody PostLectureRequestDto postLectureRequestDto) {
        PostLectureResponseDto newLecture = lectureService.createLecture(postLectureRequestDto);
        return new ResponseEntity<>(newLecture, HttpStatus.CREATED);
    }

    //선택한 강의 조회
    @GetMapping("/{lectureId}")
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    public ResponseEntity<GetLectureResponseDto> getLecture(@PathVariable Long lectureId) {
        GetLectureResponseDto selectedLecture = lectureService.getLecture(lectureId);
        return ResponseEntity.ok(selectedLecture);
    }

    // 카테고리별 강의 조회
    @GetMapping("/by-category")
    public ResponseEntity<List<GetLectureByCategoryResponseDto>> getLectureByCategory(
            @RequestParam(name = "category") CategoryEnum category,
            @RequestParam(name = "sortBy") SortByEnum sortBy,
            @RequestParam(name = "isAsc") boolean isAsc) {
        List<GetLectureByCategoryResponseDto> lecturesByCategory = lectureService.getLectureByCategory(category, sortBy, isAsc);
        return ResponseEntity.ok(lecturesByCategory);
    }

    // 선택한 강의 댓글 등록
    @PostMapping("/{lectureId}/comments")
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    public ResponseEntity<String> createComment(@PathVariable Long lectureId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostCommentRequestDto postCommentRequestDto) {
        User user = userDetails.getUser();

        lectureService.createComment(lectureId, user, postCommentRequestDto);

        return new ResponseEntity<>("댓글 등록되었습니다.", HttpStatus.CREATED);
    }

    // 선택한 강의 댓글 등록
    @PutMapping("/{lectureId}/comments/{commentId}")
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    public ResponseEntity<String> updateComment(@PathVariable Long lectureId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostCommentRequestDto postCommentRequestDto) {
        User user = userDetails.getUser();

        lectureService.updateComment(lectureId, commentId, user, postCommentRequestDto);

        return new ResponseEntity<>("댓글 수정되었습니다.", HttpStatus.OK);
    }
    @DeleteMapping("/{lectureId}/comments/{commentId}")
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    public ResponseEntity<String> deleteComment(@PathVariable Long lectureId, @PathVariable Long commentId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        lectureService.deleteComment(lectureId, commentId, user);

        return new ResponseEntity<>("댓글 삭제되었습니다.", HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{lectureId}/toLike")
    @Secured({UserRoleEnum.Authority.ADMIN, UserRoleEnum.Authority.USER})
    public ResponseEntity<String> likeLecture(@PathVariable Long lectureId,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        lectureService.likeLecture(lectureId,user);

        return new ResponseEntity<>("좋아요 상태가 업데이트되었습니다.", HttpStatus.OK);
    }
}
