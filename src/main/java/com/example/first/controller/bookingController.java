package com.example.first.controller;


import com.example.first.model.board;
import com.example.first.model.booking;
import com.example.first.repository.BookingRepository;
import com.example.first.service.BookingService;
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
public class bookingController {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/check")
    public String list(Model model, Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<booking> bookings = bookingRepository.findByNameContainingOrPhoneContaining(searchText, searchText, pageable);
        model.addAttribute("bookings", bookings);
        return "booking/check";
    }
    @GetMapping("/check/{id}")
    public String update(@PathVariable Long id ,Model model) {
        booking booking = bookingRepository.getById(id);
        booking.setActive(true);
        model.addAttribute("booking", booking);
        bookingRepository.save(booking);
        System.out.println(booking);
        return "redirect:/booking/check";
    }
    @GetMapping("/complete/delete/{id}")
    public String delete(@PathVariable Long id) {
        booking booking = bookingRepository.getById(id);
        bookingRepository.delete(booking);
        return "redirect:/booking/complete";
    }
    @GetMapping("/complete")
    public String show(){
        return "booking/complete";
    }
    @GetMapping("/result")
    public String resultView(String name, Model model){
        List<booking> bookings = bookingService.namesearch(name);
        model.addAttribute("bookings", bookings);
        return "booking/result";
    }
    @GetMapping("/booking")
    public String booking(Model model){
        model.addAttribute("booking", new booking());
        return "booking/booking";
    }
    @PostMapping("/booking")
    public String bookingSubmit(@ModelAttribute booking booking){
        bookingRepository.save(booking);
        return "redirect:/";
    }

}
