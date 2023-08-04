package com.pictocrawl.factory.image;

import com.pictocrawl.util.URLUtility;
import org.jsoup.nodes.Element;
import com.pictocrawl.model.image.GIFImage;
import com.pictocrawl.util.image.LogoDetector;

public class GIFImageFactory extends ImageFactory<GIFImage> {

    @Override
    public GIFImage createImage(int id, Element element, int websiteId) {
        String url = element.absUrl("src").trim().replaceAll("\\s", "%20");
        //String encodedUrl = new URLUtility().encode(url);
        String name = element.attr("alt").trim().isEmpty() ? "N/A" : element.attr("alt").trim();
        boolean isLogo = new LogoDetector().isLogo(element);

        return new GIFImage(id, name, isLogo, url, websiteId);
    }

}