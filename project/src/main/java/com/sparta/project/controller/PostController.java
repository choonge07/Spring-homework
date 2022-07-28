package com.sparta.project.controller;

import com.sparta.project.domain.*;
import com.sparta.project.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;

    @GetMapping("/api/posts")// 전체 게시글 목록 조회 완료
    public List<AllPost> getPost() {

        return postRepository.findAllByOrderByModifiedAtDesc();
    }
    @PostMapping("/api/posts") // 게시글 작성 완료
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    @GetMapping("/api/posts/{id}") // 게시글 조회
    public Optional<Post> getPost(@PathVariable Long id) {

        return  postRepository.findById(id);
    }
    @PostMapping("/api/posts/{id}")// 비밀번호 검증
    public Long joinPassword(@PathVariable Long id, @RequestBody PasswordDto passwordDto) {
        postService.checkPassword(id,passwordDto);
        return id;
    }

    @PutMapping("/api/posts/{id}") //게시글 수정 완료
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        postService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/posts/{id}") // 게시글 삭제 완료
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }
}
