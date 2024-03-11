package com.sparta.spring4.repository;

import com.sparta.spring4.entity.CategoryEnum;
import com.sparta.spring4.entity.Lecture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    boolean existsByLectureName(String lectureName);

    List<Lecture> findAllByCategory(CategoryEnum category, Sort sort);
}
