package com.sparta.project.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository <Post , Long> {
    List<AllPost> findAllByOrderByModifiedAtDesc();// 내림차순 정렬
}
