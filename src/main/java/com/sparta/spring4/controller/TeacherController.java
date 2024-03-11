package com.sparta.spring4.controller;

import com.sparta.spring4.dto.PostTeacherRequestDto;
import com.sparta.spring4.dto.PostTeacherResponseDto;
import com.sparta.spring4.entity.UserRoleEnum;
import com.sparta.spring4.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // 강사 등록
    @PostMapping("")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public ResponseEntity<PostTeacherResponseDto> createTeacher(@Valid @RequestBody PostTeacherRequestDto postTeacherRequestDto) {
        PostTeacherResponseDto savedTeacher = teacherService.createTeacher(postTeacherRequestDto);
        return new ResponseEntity<>(savedTeacher, HttpStatus.CREATED);
    }

    //
}
