package com.pictocrawl.factory.image;

import com.pictocrawl.model.image.JPEGImage;
import com.pictocrawl.util.image.LogoDetector;
import org.jsoup.nodes.Element;

public class JPEGImageFactory extends ImageFactory<JPEGImage> {

    @Override
    public JPEGImage createImage(int id, Element element, int websiteId) {
        String url = element.absUrl("src").trim().replaceAll("\\s", "%20");
        //String encodedUrl = new URLUtility().encode(url);
        String name = element.attr("alt").trim().isEmpty() ? "N/A" : element.attr("alt").trim();
        boolean isLogo = new LogoDetector().isLogo(element);

        return new JPEGImage(id, name, isLogo, url, websiteId);
    }

}
