package com.pictocrawl.util.image;

import com.pictocrawl.util.Validator;

import java.util.List;

public class ImageValidator implements Validator {
    private static final List<String> VALID_EXTENSIONS = List.of("gif", "jpeg", "jpg", "png", "svg");

    @Override
    public boolean validate(String extension) {
        if (extension == null || extension.trim().isEmpty()) {
            throw new IllegalArgumentException("Image extension cannot be empty or null");
        }

        return VALID_EXTENSIONS.contains(extension);
    }

}