package com.sparta.spring4.service;

import com.sparta.spring4.dto.PostTeacherRequestDto;
import com.sparta.spring4.dto.PostTeacherResponseDto;
import com.sparta.spring4.entity.Teacher;
import com.sparta.spring4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    // 강사 등록
    public PostTeacherResponseDto createTeacher(PostTeacherRequestDto postTeacherRequestDto) {
        Optional<Teacher> checkTeacherPhoneNum = teacherRepository.findTeacherByPhoneNo(postTeacherRequestDto.getPhoneNo());
        if (checkTeacherPhoneNum.isPresent()) {
            throw new IllegalArgumentException("중복된 휴대폰 번호가 존재합니다.");
        }
        Teacher newTeacher = new Teacher(postTeacherRequestDto);
        Teacher savedTeacher = teacherRepository.save(newTeacher);
        return new PostTeacherResponseDto(savedTeacher);
    }
}
