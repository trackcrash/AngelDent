package com.example.first.controller;

import com.example.first.model.board;
import com.example.first.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardRepository boardRepository;

    @RequestMapping("/list")
    public String list(Model model) {
        List<board> boards = boardRepository.findAll();
        model.addAttribute( "boards", boards);
        return "board/list";
    }

    @RequestMapping("/write")
    public String write() {
        return "board/write";
    }

    @RequestMapping("/view")
    public String view() {
        return "board/view";
    }

    @RequestMapping("/modify")
    public String modify() {
        return "board/modify";
    }

    @RequestMapping("/delete")
    public String delete() {
        return "board/delete";
    }
}
