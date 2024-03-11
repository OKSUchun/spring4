package com.sparta.spring4.service;

import com.sparta.spring4.dto.PostSignupRequestDto;
import com.sparta.spring4.entity.User;
import com.sparta.spring4.entity.UserRoleEnum;
import com.sparta.spring4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void createUser(PostSignupRequestDto postSignupRequestDto) {
        // username 중복 확인
        String username = postSignupRequestDto.getUsername();
        String password = passwordEncoder.encode(postSignupRequestDto.getPassword());
        String gender = postSignupRequestDto.getGender();
        String phone_num = postSignupRequestDto.getPhone_num();
        String address = postSignupRequestDto.getAddress();
        UserRoleEnum role = postSignupRequestDto.getRole();

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        Optional<User> checkPhoneNum = userRepository.findByUsername(phone_num);
        if (checkPhoneNum.isPresent()) {
            throw new IllegalArgumentException("중복된 휴대폰 번호가 존재합니다.");
        }

        User user = new User(username, password, gender, phone_num, address, role);
        userRepository.save(user);
    }
}
