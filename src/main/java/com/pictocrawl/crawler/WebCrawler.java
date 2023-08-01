package com.pictocrawl.crawler;

import com.pictocrawl.http.DefaultHttpClientSetup;
import com.pictocrawl.model.image.Image;
import com.pictocrawl.util.parser.HtmlParser;
import com.pictocrawl.util.parser.RobotsTxtParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class WebCrawler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebCrawler.class);
    private static final int MAX_THREADS = 20;

    private final ExecutorService crawlingThreadPool;
    private final ExecutorService parsingThreadPool;
    private final String startUrl;
    private final ConcurrentMap<String, Boolean> visited = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Set<Image>> images = new ConcurrentHashMap<>();
    private final HtmlParser parser;
    private final RobotsTxtParser robotsTxtParser;

    private Map<String, List<String>> robotsTxt;

    public WebCrawler(String startUrl) {
        this(startUrl, new HtmlParser(startUrl, new DefaultHttpClientSetup(MAX_THREADS + 10, MAX_THREADS).setupHttpClient()),
                new RobotsTxtParser(startUrl));
    }

    public WebCrawler(String startUrl, HtmlParser parser, RobotsTxtParser robotsTxtParser) {
        if (checkUrl(startUrl)) {
            this.startUrl = startUrl;
        } else {
            throw new IllegalArgumentException("URL cannot be null, empty, or blank");
        }

        this.parser = parser;
        this.robotsTxtParser = robotsTxtParser;

        this.crawlingThreadPool = new ThreadPoolService(MAX_THREADS, MAX_THREADS * 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        this.parsingThreadPool = new ThreadPoolService(MAX_THREADS, MAX_THREADS * 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    public String getStartUrl() {
        return startUrl;
    }

    public Map<String, List<String>> getRobotsTxt() {
        return robotsTxt;
    }

    public ConcurrentMap<String, Set<Image>> crawl() {
        if (checkRobotsTxt()) {
            visited.put(startUrl, true);
            AtomicInteger activeTasks = new AtomicInteger(1); // Initialize with 1 for the seed URL task

            crawlingThreadPool.execute(new Crawl(startUrl, parser, visited, images, crawlingThreadPool, parsingThreadPool, activeTasks));

            // Wait for all tasks to complete
            try {
                while (activeTasks.get() > 0) {
                    Thread.sleep(1000); // Wait for 1 second
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage());
            }

            // Shutdown the thread pools after all tasks are completed
            crawlingThreadPool.shutdown();
            parsingThreadPool.shutdown();
        }

        return images;
    }

    private boolean checkUrl(String url) {
        return url != null && !url.trim().isEmpty();
    }

    private boolean checkRobotsTxt() {
        LOGGER.info("Checking robots.txt file...");
        robotsTxt = robotsTxtParser.checkAndGet();

        if (!robotsTxt.isEmpty()) {
            List<String> allUserAgents = robotsTxt.get("*");

            if (allUserAgents != null && allUserAgents.contains("/")) {
                LOGGER.info("Crawling disallowed by robots.txt");
                return false;
            } else {
                robotsTxt.entrySet().stream()
                        .filter(entry -> entry.getKey().equals("*"))
                        .flatMap(entry -> entry.getValue().stream())
                        .filter(disallow -> !robotsTxt.containsKey(disallow))
                        .forEach(this::processDisallowEntry);
            }

        }

        return true;
    }

    private void processDisallowEntry(String disallow) {
        String url = startUrl.substring(0, startUrl.lastIndexOf('/')) + disallow;
        visited.put(url, true);
    }

}