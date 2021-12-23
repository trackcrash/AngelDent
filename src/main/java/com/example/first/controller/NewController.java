package com.example.first.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
@Controller
public class NewController {
    @GetMapping("/sample")
    public String sampleFirst(Model model) {
        model.addAttribute("name","홍길동");
        return "sample";
    }
}
