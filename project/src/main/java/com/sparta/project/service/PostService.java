package com.sparta.project.service;

import com.sparta.project.domain.PasswordDto;
import com.sparta.project.domain.Post;
import com.sparta.project.domain.PostRepository;
import com.sparta.project.domain.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional // 업데이트
    public Long update (Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        post.update(requestDto);
        return post.getId();
    }

    public void checkPassword(Long id, PasswordDto passwordDto) { // 비밀번호 검증
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        if (post.getPassword() != passwordDto.getPassword()){
            throw new IllegalStateException("패스워드가 다릅니다.");
        }
    }
}
