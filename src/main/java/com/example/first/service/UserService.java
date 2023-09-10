package com.example.first.service;

import com.example.first.model.member;
import com.example.first.model.role;
import com.example.first.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    //패스워드 암호화
    @Autowired
    private PasswordEncoder passwordEncoder;
    //패스워드 저장용
    public member save(member user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnable(true);
        role role = new role();
        role.setId(1l);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
