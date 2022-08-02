package com.sparta.project.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // nullable : null 허용 여부 , unique: 중복 허용 여부(false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String password;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
