package com.pictocrawl.factory.image;

import com.pictocrawl.model.image.SVGImage;
import com.pictocrawl.util.URLUtility;
import com.pictocrawl.util.image.LogoDetector;
import org.jsoup.nodes.Element;

public class SVGImageFactory extends ImageFactory<SVGImage> {

    @Override
    public SVGImage createImage(int id, Element element) {
        String url = element.absUrl("src").trim().replaceAll("\\s", "%20");
        //String encodedUrl = new URLUtility().encode(url);
        String name = element.attr("alt").trim().isEmpty() ? "N/A" : element.attr("alt").trim();
        boolean isLogo = new LogoDetector().isLogo(element);

        return new SVGImage(id, name, isLogo, url);
    }

}