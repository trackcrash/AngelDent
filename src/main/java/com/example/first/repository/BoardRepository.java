package com.example.first.repository;

import com.example.first.model.board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<board, Long> {

}
