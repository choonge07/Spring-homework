package com.sparta.project.entity;

import java.time.LocalDateTime;

public interface AllPost { // 전체 게시글 조회용
    String getTitle();
    String getAuthor();
    LocalDateTime getCreateAt();
}