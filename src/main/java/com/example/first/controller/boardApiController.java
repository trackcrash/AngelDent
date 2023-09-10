package com.example.first.controller;

import com.example.first.model.board;
import com.example.first.repository.BoardRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
public class boardApiController {

    private final BoardRepository repository;

    public boardApiController(BoardRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<board> getAllBoards(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "") String contents) {
        if (title.isEmpty() && contents.isEmpty()) {
            return repository.findAll();
        } else {
            return repository.findByTitleOrContents(title, contents);
        }
    }

    @GetMapping("/{id}")
    public board getBoard(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public board createBoard(@RequestBody board board) {
        return repository.save(board);
    }

    @PutMapping("/{id}")
    public board updateBoard(@RequestBody board newBoard, @PathVariable Long id) {
        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContents(newBoard.getContents());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}