package com.example.first.controller;

import com.example.first.model.board;
import com.example.first.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class boardApiController {

    @Autowired
    private BoardRepository repository;

    @GetMapping("/board")
    List<board> all(@RequestParam(required = false,defaultValue = "") String title,@RequestParam(required = false,defaultValue="") String contents) {
        if(StringUtils.isEmpty(title) && StringUtils.isEmpty(contents)){
        return repository.findAll();
        } else {
            return repository.findByTitleOrContents(title,contents);
        }
    }
    // end::get-aggregate-root[]

    @PostMapping("/board")
    board newboard(@RequestBody board newboard) {
        return repository.save(newboard);
    }

    // Single item

    @GetMapping("/board/{id}")
    board one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);
    }

    @PutMapping("/board/{id}")
    board replaceboard(@RequestBody board newboard, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setTitle(newboard.getTitle());
                    employee.setContents(newboard.getContents());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newboard.setId(id);
                    return repository.save(newboard);
                });
    }

    @DeleteMapping("/board/{id}")
    void deleteboard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
