package com.pictocrawl.util.parser;

import com.pictocrawl.config.LoggingConfig;
import com.pictocrawl.util.io.RobotsTxtFileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotsTxtParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsTxtParser.class);
    private static final String ROBOTS_TXT = "/robots.txt";

    private final RobotsTxtFileHandler fileHandler;
    private final String url;
    private final Map<String, List<String>> robotsTxt;
    private final List<String> userAgentLines;
    private final List<String> disallowLines;
    private final List<String> allowLines;

    public RobotsTxtParser(String url) {
        LoggingConfig.configure(RobotsTxtParser.class);

        this.fileHandler = new RobotsTxtFileHandler();
        this.url = url;
        this.userAgentLines = new ArrayList<>();
        this.disallowLines = new ArrayList<>();
        this.allowLines = new ArrayList<>();
        this.robotsTxt = new HashMap<>();
    }

    // TODO: Implement feature to parse site's sitemap.xml if present
    public Map<String, List<String>> checkAndGet() {
        try {
            if (hasRobotsTxtFile()) {
                String robotsTxtContent = readRobotsTxtContent();

                if (robotsTxtContent != null) {
                    parseRobotsTxtContent(robotsTxtContent);
                }
            }
        } catch (IOException e) {
            LOGGER.warn("{} has no robots.txt file", url);
        }

        return robotsTxt;
    }

    // TODO: Use HTTP connection pool instead of HttpsURLConnection
    private boolean hasRobotsTxtFile() throws IOException {
        URL url = new URL(this.url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        int statusCode = connection.getResponseCode();

        return statusCode == HttpsURLConnection.HTTP_OK;
    }

    private String readRobotsTxtContent() {
        String directory = url.substring(0, url.lastIndexOf('/'));
        return fileHandler.readObjectFromFile(String.class, ROBOTS_TXT, directory);
    }

    private void parseRobotsTxtContent(String fileContent) {
        try (BufferedReader reader = new BufferedReader(new StringReader(fileContent))) {
            String userAgent = null;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.toLowerCase().startsWith("user-agent:")) {
                    if (userAgent != null && !disallowLines.isEmpty()) {
                        robotsTxt.put(userAgent, new ArrayList<>(disallowLines));
                    }

                    userAgent = line.substring("user-agent:".length()).trim();
                    disallowLines.clear();
                } else if (line.toLowerCase().startsWith("disallow:")) {
                    String disallowPath = line.substring("disallow:".length()).trim();
                    disallowLines.add(disallowPath);
                }
            }

            if (userAgent != null && !disallowLines.isEmpty()) {
                robotsTxt.put(userAgent, new ArrayList<>(disallowLines));
            }
        } catch (IOException e) {
            LOGGER.error("Error parsing robots.txt content: {}", e.getMessage());
        }
    }

}