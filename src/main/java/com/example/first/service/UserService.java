package com.example.first.service;

import com.example.first.model.member;
import com.example.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //패스워드 암호화
    private final PasswordEncoder passwordEncoder;


    //패스워드 저장용
    public member save(member user) {
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new RuntimeException("이미가입된 아이디입니다");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnable(true);
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
}
