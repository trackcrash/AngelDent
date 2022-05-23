package com.example.first.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class chatController {
    @GetMapping("/chat/webchat")
    public String chat(Model model) {

        model.addAttribute("sender");
        model.addAttribute("message");
        model.addAttribute("sessionNum");
        return("/chat/webchat");
    }

}
