package com.example.first.controller;

import org.springframework.web.bind.annotation.RequestMapping;


public class chatController {
    @RequestMapping("/webchat")
    public String chat() {
        return "webchat";
    }
}
