package com.example.first.service;

import com.example.first.model.booking;
import com.example.first.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Transactional
    public List<booking> namesearch(String name) {
        List<booking> bookings = bookingRepository.findByNameContaining(name);
        return bookings;
    }
}
