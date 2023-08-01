package com.pictocrawl.util.domain;

import com.pictocrawl.config.LoggingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

public class DomainResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DomainResolver.class);

    public DomainResolver() {
        LoggingConfig.configure(DomainResolver.class);
    }

    public String getDomain(String url) {
        if (url == null) {
            LOGGER.error("URL cannot be null");
            throw new IllegalArgumentException();
        }

        try {
            URI uri = new URI(url);
            validateUri(uri);

            return uri.getHost();
        } catch (URISyntaxException e) {
            LOGGER.error("Failed to parse URL: {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    private void validateUri(URI uri) {
        if (uri.getScheme() == null || uri.getHost() == null) {
            throw new IllegalArgumentException("URL cannot be resolved: " + uri);
        }
    }

}