package com.sparta.spring4.controller;

import com.sparta.spring4.dto.GetLectureByCategoryResponseDto;
import com.sparta.spring4.dto.GetLectureResponseDto;
import com.sparta.spring4.dto.PostLectureRequestDto;
import com.sparta.spring4.dto.PostLectureResponseDto;
import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.SortByEnum;
import com.sparta.spring4.entity.UserRoleEnum;
import com.sparta.spring4.service.LectureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
            @RequestParam(name="category") CategoryEnum category,
            @RequestParam(name="sortBy") SortByEnum sortBy,
            @RequestParam(name="isAsc") boolean isAsc) {
        List<GetLectureByCategoryResponseDto> lecturesByCategory = lectureService.getLectureByCategory(category, sortBy, isAsc);
        return ResponseEntity.ok(lecturesByCategory);
    }

}
