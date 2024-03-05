package com.example.first.controller;


import com.example.first.model.board;
import com.example.first.model.booking;
import com.example.first.repository.BookingRepository;
import com.example.first.service.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;


@Controller
@RequestMapping("booking")
@Slf4j
public class bookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/approve")
    public String list(Model model, Pageable pageable, @RequestParam(required = false) String status) {
        // status에 따라 조건 분기하여 데이터 조회
        Page<booking> bookings;
        if ("active".equals(status)) {
            // 활성 예약 조회 로직
               bookings = bookingService.findActiveBookingsBySearchText(pageable);
            //로깅
            log.info("활성 들어옴 : {}", bookings);
        } else {
            // 비활성 예약 조회 로직
            bookings = bookingService.findInactiveBookingsBySearchText(pageable);
            //로깅
            log.info("비활성 들어옴 : {}", bookings);
        }
        model.addAttribute("bookings", bookings);
        model.addAttribute("currentStatus", status);
        return "booking/approve";
    }

    @GetMapping("/check/{id}")
    public String update(@PathVariable Long id) {
        bookingService.updateBookingActiveStatus(id, true);
        return "redirect:/booking/approve";
    }

    @GetMapping("/complete")
    public String completeList(Model model, Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        model.addAttribute("bookings", bookingService.findBookingsBySearchText(searchText, pageable));
        return "booking/complete";
    }

    @GetMapping("/complete/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/";
    }

    @GetMapping("/result")
    public String resultView(@RequestParam String name, Model model) {
        model.addAttribute("bookings", bookingService.findBookingsByName(name));
        return "booking/result";
    }

    @GetMapping("/booking")
    public String bookingForm(Model model) {
        model.addAttribute("booking", new booking());
        return "booking/booking";
    }

    @PostMapping("/booking")
    public String bookingSubmit(@ModelAttribute booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/";
    }
}
