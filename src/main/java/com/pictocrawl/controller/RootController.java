package com.pictocrawl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String welcome(Model model) {
        String welcomeMessage = "Hello, world!";
        model.addAttribute("welcome", welcomeMessage);

        return "index";
    }

}