package com.sparta.spring4.controller;

import com.sparta.spring4.dto.PostSignupRequestDto;
import com.sparta.spring4.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@Valid @RequestBody PostSignupRequestDto postSignupRequestDto) {

        userService.createUser(postSignupRequestDto);
        return new ResponseEntity<>("회원가입되었습니다.", HttpStatus.CREATED);
    }


}
