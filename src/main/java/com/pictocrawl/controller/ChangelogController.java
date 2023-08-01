package com.pictocrawl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChangelogController {

    @GetMapping("/changelog")
    public String about(Model model) {
        return "changelog";
    }

}
