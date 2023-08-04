package com.pictocrawl.factory.image;

import com.pictocrawl.model.image.Image;
import org.jsoup.nodes.Element;


public abstract class ImageFactory<T extends Image> {

    public abstract T createImage(int id, Element element, int websiteId);

}