package com.example.first.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.metamodel.SetAttribute;

@Controller
public class chatController {
    @GetMapping("/chat/webchat")
    public String chat() {
        return("chat/webchat");
    }
    @PostMapping("/chat/webchat")
    public String send(){

        return("chat/webchat");
    }
}
