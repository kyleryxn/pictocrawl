package com.pictocrawl.controller;

import com.pictocrawl.model.image.Image;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CrawlController {
    private Set<Image> images;

    @GetMapping("/crawl")
    public String crawl() {


        return "crawl";
    }

}
