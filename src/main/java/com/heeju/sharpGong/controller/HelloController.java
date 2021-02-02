package com.heeju.sharpGong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String Hello(Model model){
//        model.addAttribute("loginForm", new LoginForm());
//        return "/login";
        model.addAttribute("data","hello");
        return "/home";
    }
}
