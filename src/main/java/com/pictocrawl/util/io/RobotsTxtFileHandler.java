package com.pictocrawl.util.io;

import com.pictocrawl.config.LoggingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RobotsTxtFileHandler implements ObjectReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(RobotsTxtFileHandler.class);

    public RobotsTxtFileHandler() {
        LoggingConfig.configure(RobotsTxtFileHandler.class);
    }

    @Override
    public <T> T readObjectFromFile(Class<T> type, String filename, String directory) {
        String content;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(directory + filename).openStream()))) {
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            content = builder.toString();
        } catch (IOException e) {
            LOGGER.error("Cannot connect to URL: {}", e.getMessage());
            return null;
        }

        return type.cast(content);
    }

}