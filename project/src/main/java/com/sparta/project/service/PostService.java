package com.sparta.project.service;

import com.sparta.project.dto.ResponseDto;
import com.sparta.project.entity.Post;
import com.sparta.project.entity.User;
import com.sparta.project.repository.PostRepository;
import com.sparta.project.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto) {

        Post post = new Post(requestDto);

        postRepository.save(post);

        return ResponseDto.success(post);
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) { // 상세 조회
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        return ResponseDto.success(optionalPost.get());
    }

    @Transactional(readOnly = true) // 전체조회
    public ResponseDto<?> getAllPost() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc());
    }

    @Transactional
    public ResponseDto<Post> updatePost(Long id, PostRequestDto requestDto) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NULL_POST_ID", "post id isn't exist");
        }

        Post post = optionalPost.get();
        post.update(requestDto);

        return ResponseDto.success(post);
    }

    @Transactional
    public ResponseDto<?> deletePost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }

        Post post = optionalPost.get();

        postRepository.delete(post);

        return ResponseDto.success(true);
    }

    @Transactional(readOnly = true)
    public ResponseDto<?> validateAuthorByPassword(Long id, String password) {
        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) {
            return ResponseDto.fail("NOT_FOUND", "post id is not exist");
        }
        Post post = optionalPost.get();

        if (post.getPassword().equals(password)) {
            return ResponseDto.fail("PASSWORD_NOT_CORRECT", "password is not correct");
        }

        return ResponseDto.success(true);
    }
}
