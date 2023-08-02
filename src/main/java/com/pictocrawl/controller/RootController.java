package com.pictocrawl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class RootController {

    @GetMapping("/")
    public String welcome(Model model) {
        String welcomeMessage = "Hello, world!";
        model.addAttribute("welcome", welcomeMessage);

        // Simulated stats
        int avgPages = new Random().nextInt(10_000);
        int totalWebsites = new Random().nextInt(10_000);
        int totalImages = new Random().nextInt(1_000_000);
        int avgImagePerPage = new Random().nextInt(10_000);
        double speed = Math.round(new Random().nextDouble(50));

        model.addAttribute("avgPages", avgPages);
        model.addAttribute("totalWebsites", totalWebsites);
        model.addAttribute("totalImages", totalImages);
        model.addAttribute("avgImagePerPage", avgImagePerPage);
        model.addAttribute("speed", speed);

        return "index";
    }

}