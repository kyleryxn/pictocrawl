package com.pictocrawl.controller;

import com.pictocrawl.crawler.WebCrawler;
import com.pictocrawl.model.image.Image;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Controller
public class CrawlController {

    @GetMapping("/crawl")
    public String showForm() {
        return "crawl";
    }

    @PostMapping("/crawl/result")
    public String processCrawl(@RequestParam(value = "startURL") String startURL,
                               @RequestParam(value = "imageType", required = false) List<String> imageTypes,
                               @RequestParam(value = "all", required = false) boolean selectAll,
                               Model model) {
        ConcurrentMap<String, Set<Image>> result = new WebCrawler(startURL).crawl();

        Set<Image> images = result.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toSet());

        if (selectAll) {
            model.addAttribute("images", images);
        } else if (imageTypes != null && !imageTypes.isEmpty()) {
            Set<Image> filteredImages = images.stream()
                    .filter(img -> imageTypes.contains(img.getType().toLowerCase()))
                    .collect(Collectors.toSet());

            model.addAttribute("images", filteredImages);
        }

        return "crawlresult";
    }

}
