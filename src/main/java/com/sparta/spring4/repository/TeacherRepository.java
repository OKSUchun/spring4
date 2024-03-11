package com.sparta.spring4.repository;

import com.sparta.spring4.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findTeacherByPhoneNo(String phoneNo);
}
