package com.sparta.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 자동 생성자
@Getter
@Entity // 테이블로 인식
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @JsonIgnore
    private Long id ;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String content;
    @Column
    @JsonIgnore
    private int password;
    @CreatedDate
    LocalDateTime createAt;

    public Post (PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) { // 업데이트
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.content = requestDto.getContent();
        this.password = requestDto.getPassword();
    }
}
