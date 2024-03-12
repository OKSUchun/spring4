package com.sparta.spring4.repository;

import com.sparta.spring4.entity.Lecture;
import com.sparta.spring4.entity.Like;
import com.sparta.spring4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndLecture(User user, Lecture lecture);
}
