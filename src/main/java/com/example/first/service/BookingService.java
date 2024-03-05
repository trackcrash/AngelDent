package com.example.first.service;

import com.example.first.model.booking;
import com.example.first.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;

    @Transactional
    public List<booking> findBookingsByName(String name) {
        return bookingRepository.findByNameContaining(name);
    }

    @Transactional
    public Page<booking> findBookingsBySearchText(String searchText, Pageable pageable) {
        return bookingRepository.findByNameContainingOrPhoneContaining(searchText, searchText, pageable);
    }
    @Transactional
    public Page<booking> findActiveBookingsBySearchText(Pageable pageable) {
        return bookingRepository.findByActive(true, pageable);
    }
    @Transactional
    public Page<booking> findInactiveBookingsBySearchText(Pageable pageable) {
        return bookingRepository.findByActive(false, pageable);
    }

    @Transactional
    public booking getBookingById(Long id) {
        return bookingRepository.getById(id);
    }

    @Transactional
    public booking saveBooking(booking booking) {
        return bookingRepository.save(booking);
    }

    @Transactional
    public void deleteBooking(Long id) {
        booking booking = bookingRepository.getById(id);
        bookingRepository.delete(booking);
    }

    @Transactional
    public booking updateBookingActiveStatus(Long id, boolean status) {
        booking booking = bookingRepository.getById(id);
        booking.setActive(status);
        return bookingRepository.save(booking);
    }

    public void changeBookingStatus(Long id) {
        booking booking = bookingRepository.getById(id);
        booking.setActive(true);
        bookingRepository.save(booking);
    }
}
