package com.sparta.project.entity;

import com.sparta.project.dto.CommentRequestDto;
import com.sparta.project.security.UserDetailsImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    public Comment(CommentRequestDto requestDto, Post post) {
        this.content = requestDto.getContent();
        this.post = post;
//        this.user = user;
    }
}
