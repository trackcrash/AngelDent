package com.example.first.repository;

import com.example.first.model.board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<board, Long> {
    List<board> findByTitle(String keyword);
    List<board> findByTitleOrContents(String title, String contents);

    Page<board> findByTitleContainingOrContentsContaining(String title, String contents, Pageable pageable);
}
