package com.pictocrawl.util.image;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.nodes.Element;

import java.util.Objects;
import java.util.Optional;

public class ExtensionExtractor implements Extractor {

    @Override
    public String getExtension(String url) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }

        int queryIndex = url.lastIndexOf('?');

        // Remove any query parameters if present
        if (queryIndex != -1) {
            url = url.substring(0, queryIndex);
        }

        String extension = FilenameUtils.getExtension(url);

        return !extension.isEmpty() ? extension : "No extension detected";
    }

    @Override
    public String getExtension(Element tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }

        String url = tag.absUrl("src");
        return getExtension(url);
    }

}