package com.example.first.repository;

import com.example.first.model.board;
import com.example.first.model.member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<member, Long> {
}
