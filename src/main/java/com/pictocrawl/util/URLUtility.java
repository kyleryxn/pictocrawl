package com.pictocrawl.util;

import java.util.List;

public class URLUtility implements Validator {
    private static final List<String> INVALID_EXTENSIONS = List.of("#", "javascript:", "mailto:", "data:", "ftp:", ".pdf");

    public String trimURL(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty or null");
        }

        int index = url.indexOf("#");

        if (index != -1) {
            return url.substring(0, index);
        }

        return url;
    }


    @Override
    public boolean validate(String url) {
        if (url == null || url.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }

        for (String ext : INVALID_EXTENSIONS) {
            if (url.startsWith(ext) || url.endsWith(ext)) {
                return false;
            }
        }

        return true;
    }

    /*public String encode(String url) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.uri().toASCIIString();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            LOGGER.error(e.getMessage());
        }

        return url;
    }*/
}
