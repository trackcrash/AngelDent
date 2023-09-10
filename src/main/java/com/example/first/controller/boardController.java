package com.example.first.controller;

import com.example.first.model.board;
import com.example.first.repository.BoardRepository;
import com.example.first.service.BoardService;
import com.example.first.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class boardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;
    // 글 목록
    @GetMapping("/list")
    public String list(Model model ,Pageable pageable,@RequestParam(required = false,defaultValue = "") String searchText) {
 //       Page<board> boards = boardRepository.findAll(pageable);
        Page<board> boards = boardRepository.findByTitleContainingOrContentsContaining(searchText, searchText, pageable);
        int startPage = Math.max(1 ,boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
        return "board/write";
    }
    // 글 저장
    @PostMapping("/write")
    public String writeSubmit(@Valid board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "board/write";
        }
        String username = authentication.getName();
        boardService.save(username,board);
        return "redirect:/board/list";
    }
    //글 보여주기
    @GetMapping("/view/{id}")
    public String view(Model model, @PathVariable Long id) {
        board board = boardRepository.getById(id);
        model.addAttribute("board", board);
        return "board/view";
    }
    //글 수정
    @GetMapping("/modify/{id}")
    public String modify(Model model, @PathVariable Long id) {
        board board = boardRepository.getById(id);
        model.addAttribute("board", board);
        return "board/modify";
    }
    @PostMapping("/modify")
    public String modify(@ModelAttribute board board ,@RequestParam Long id,Authentication authentication) {
            String username = authentication.getName();
            boardService.save(username, board);
        return "redirect:/board/list";
    }
    //글 삭제
    @GetMapping (value = "/delete/{id}")
    public String deleteboard(@PathVariable Long id) {
        board board = boardRepository.getById(id);
        boardRepository.deleteById(id);
        return "redirect:/board/list";
    }
}
