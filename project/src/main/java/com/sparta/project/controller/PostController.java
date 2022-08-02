package com.sparta.project.controller;

import com.sparta.project.dto.ResponseDto;
import com.sparta.project.dto.PostRequestDto;
import com.sparta.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/api/post/{id}") // 게시글 상세 조회
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/post") // 전체 조회
    public ResponseDto<?> getAllPosts() {
        return postService.getAllPost();
    }

    @PutMapping("/api/post/{id}")
    public ResponseDto<?> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/api/post/{id}")
    public ResponseDto<?> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @PostMapping("/api/post/{id}")
    public ResponseDto<?> validateAuthorByPassword(@PathVariable Long id, @RequestBody String password) {
        return postService.validateAuthorByPassword(id, password);
    }

}
