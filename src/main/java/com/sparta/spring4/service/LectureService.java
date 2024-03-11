package com.sparta.spring4.service;

import com.sparta.spring4.dto.GetLectureByCategoryResponseDto;
import com.sparta.spring4.dto.GetLectureResponseDto;
import com.sparta.spring4.dto.PostLectureRequestDto;
import com.sparta.spring4.dto.PostLectureResponseDto;
import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import com.sparta.spring4.entity.SortByEnum;
import com.sparta.spring4.entity.Teacher;
import com.sparta.spring4.repository.LectureRepository;
import com.sparta.spring4.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureService {
    private final TeacherRepository teacherRepository;
    private final LectureRepository lectureRepository;

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
    // 강의 조회
    public GetLectureResponseDto getLecture(Long lectureId) {
        return new GetLectureResponseDto(findLecture(lectureId));
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
}
