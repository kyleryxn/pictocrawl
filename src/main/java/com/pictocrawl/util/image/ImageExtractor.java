package com.pictocrawl.util.image;

import com.pictocrawl.model.image.Image;

import java.util.Set;

public interface ImageExtractor {

    Set<Image> getImages(String url);

}