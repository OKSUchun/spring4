package com.sparta.spring4.entity;

import com.sparta.spring4.dto.PostSignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String phone_num;

    @Column
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public User(String username, String password, String gender, String phone_num, String address, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.phone_num = phone_num;
        this.address = address;
        this.role = role;
    }
}
