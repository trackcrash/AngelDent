package com.example.first.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NewController {
    @GetMapping
    public String index(){
        return "index";
    }
    @RequestMapping("/reserv/reserv")
    public String reserv(){return "/reserv/reserv";}
}

