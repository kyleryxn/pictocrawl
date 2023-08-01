package com.pictocrawl.factory.image;

import com.pictocrawl.model.image.JPGImage;
import com.pictocrawl.util.URLUtility;
import org.jsoup.nodes.Element;
import com.pictocrawl.util.image.LogoDetector;

public class JPGImageFactory extends ImageFactory<JPGImage> {

    @Override
    public JPGImage createImage(int id, Element element) {
        String url = element.absUrl("src").trim().replaceAll("\\s", "%20");
        //String encodedUrl = new URLUtility().encode(url);
        String name = element.attr("alt").trim().isEmpty() ? "N/A" : element.attr("alt").trim();
        boolean isLogo = new LogoDetector().isLogo(element);

        return new JPGImage(id, name, isLogo, url);
    }

}