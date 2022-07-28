package com.sparta.project.domain;


import lombok.Getter;

import java.time.LocalDateTime;
public interface AllPost {
    String getTitle();
    String getAuthor();
    LocalDateTime getCreateAt();
}
