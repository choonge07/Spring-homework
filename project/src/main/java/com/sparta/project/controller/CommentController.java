package com.sparta.project.controller;

import com.sparta.project.dto.CommentRequestDto;
import com.sparta.project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/api/comments")
    public ResponseEntity<?> createComment(@RequestParam Long postId,
                                           @RequestBody CommentRequestDto requestDto
                                           ) {
        Long createdCommentId =  commentService.createComment(postId, requestDto);
        return ResponseEntity.ok().body(createdCommentId);
    }

}
