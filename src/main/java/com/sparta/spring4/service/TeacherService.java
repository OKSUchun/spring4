package com.sparta.spring4.service;

import com.sparta.spring4.dto.PostTeacherRequestDto;
import com.sparta.spring4.dto.PostTeacherResponseDto;
import com.sparta.spring4.entity.Teacher;
import com.sparta.spring4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    // 강사 등록
    public PostTeacherResponseDto createTeacher(PostTeacherRequestDto postTeacherRequestDto) {
        Teacher newTeacher = new Teacher(postTeacherRequestDto);
        Teacher savedTeacher = teacherRepository.save(newTeacher);
        return new PostTeacherResponseDto(savedTeacher);
    }
}
