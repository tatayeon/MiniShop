package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickName;

    private String password;

    private String email;

    public User(String nickName, String password, String email) {
        this.nickName = nickName;
        this.password = password;
        this.email = email;
    }

}
