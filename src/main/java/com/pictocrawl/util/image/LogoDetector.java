package com.pictocrawl.util.image;

import org.jsoup.nodes.Element;

import java.util.stream.StreamSupport;

public class LogoDetector implements Detector {

    @Override
    public boolean isLogo(Element img) {
        if (img == null) {
            throw new IllegalArgumentException("Element cannot be null.");
        }

        // Loop through element's attributes and see if they contain "logo" or "brand"
        return StreamSupport.stream(img.attributes().spliterator(), false) // set to 'true' to enable parallel streams
                .anyMatch(attribute -> {
                    String attributeValue = attribute.getValue().toLowerCase();
                    return attributeValue.contains("logo") || attributeValue.contains("brand");
                });
    }

}