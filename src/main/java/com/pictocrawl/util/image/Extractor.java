package com.pictocrawl.util.image;

import org.jsoup.nodes.Element;

public interface Extractor {

    String getExtension(String url);

    String getExtension(Element tag);

}