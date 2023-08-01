package com.pictocrawl.crawler;

import com.pictocrawl.model.image.Image;
import com.pictocrawl.util.parser.HtmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class Crawl implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Crawl.class);

    private final String url;
    private final HtmlParser parser;
    private final ConcurrentMap<String, Boolean> visited;
    private final ConcurrentMap<String, Set<Image>> images;
    private final ExecutorService crawlingThreadPool;
    private final ExecutorService parsingThreadPool;
    private final AtomicInteger activeTasks;

    public Crawl(String url, HtmlParser parser, ConcurrentMap<String, Boolean> visited, ConcurrentMap<String, Set<Image>> images,
                 ExecutorService crawlingThreadPool, ExecutorService parsingThreadPool, AtomicInteger activeTasks) {
        this.url = url;
        this.parser = parser;
        this.visited = visited;
        this.images = images;
        this.crawlingThreadPool = crawlingThreadPool;
        this.parsingThreadPool = parsingThreadPool;
        this.activeTasks = activeTasks;
    }

    @Override
    public void run() {
        LOGGER.info("Visiting {}", url);

        Set<String> urls = parser.getUrls(url);

        for (String link : urls) {
            visited.putIfAbsent(link, false);

            if (!visited.get(link)) {
                visited.put(link, true);

                activeTasks.incrementAndGet(); // Increment the activeTasks counter for the new task

                parsingThreadPool.execute(() -> {
                    Set<Image> imagesOnPage = parser.getImages(link);
                    images.put(link, imagesOnPage);

                    crawlingThreadPool.execute(new Crawl(link, parser, visited, images, crawlingThreadPool,
                            parsingThreadPool, activeTasks));

                    activeTasks.decrementAndGet(); // Decrement the activeTasks counter after completion
                });
            }
        }

        activeTasks.decrementAndGet(); // Decrement the activeTasks counter after completion of the current task
    }

}