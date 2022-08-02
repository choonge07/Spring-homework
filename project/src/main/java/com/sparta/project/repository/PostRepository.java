package com.sparta.project.repository;

import com.sparta.project.entity.AllPost;
import com.sparta.project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository <Post, Long> {
    Optional<Post> findById(Long id);
    List<AllPost> findAllByOrderByModifiedAtDesc();// 내림차순 정렬
}
