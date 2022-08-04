package com.sparta.project.service;

import com.sparta.project.dto.CommentRequestDto;
import com.sparta.project.entity.Comment;
import com.sparta.project.entity.Post;
import com.sparta.project.repository.CommentRepository;
import com.sparta.project.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;



    @Transactional
    public Long createComment(Long postId, CommentRequestDto requestDto) {
        System.out.println(postId);
        System.out.println(requestDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(requestDto,post);

        return commentRepository.save(comment).getId();
    }
}
