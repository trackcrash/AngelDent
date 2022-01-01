package com.example.first.controller;

import com.example.first.model.board;
import com.example.first.repository.BoardRepository;
import com.example.first.validator.BoardValidator;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;
    // 글 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<board> boards = boardRepository.findAll();
        model.addAttribute( "boards", boards);
        return "board/list";
    }
    // 새 글 작성 화면
    @GetMapping("/write")
    public String write(Model model, @RequestParam(required = false) Long id) {
        // 새 글 쓰기
        if (id == null) {
        model.addAttribute("board", new board());
        }
        // 수정일 경우
        else {
            board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/write";
    }
    // 글 저장
    @PostMapping("/write")
    public String writeSubmit(@Valid board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
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
