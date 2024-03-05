package com.example.first.repository;

import com.example.first.model.booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<booking, Long> {
    Page<booking> findByNameContainingOrPhoneContaining(String name, String phone, Pageable pageable);
    Page<booking> findByActive(boolean active, Pageable pageable);

    List<booking> findByNameContaining(String name);
}

