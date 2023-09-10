package com.example.first.service;

import com.example.first.model.board;
import com.example.first.model.member;
import com.example.first.repository.BoardRepository;
import com.example.first.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardrepository;

    @Autowired
    private UserRepository userRepository;

    public board save(String username,board board) {
        member member = userRepository.findByUsername(username);
        board.setUser(member);
        return boardrepository.save(board);
    }
}
