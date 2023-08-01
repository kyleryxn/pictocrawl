package com.pictocrawl.util.parser;

import com.pictocrawl.factory.image.*;
import com.pictocrawl.model.image.Image;
import com.pictocrawl.util.URLUtility;
import com.pictocrawl.util.Validator;
import com.pictocrawl.util.domain.Comparator;
import com.pictocrawl.util.domain.DomainComparator;
import com.pictocrawl.util.domain.DomainResolver;
import com.pictocrawl.util.image.ExtensionExtractor;
import com.pictocrawl.util.image.Extractor;
import com.pictocrawl.util.image.ImageExtractor;
import com.pictocrawl.util.image.ImageValidator;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class HtmlParser implements HtmlDocumentProvider, URLExtractor, ImageExtractor {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlParser.class);
    private static final Map<String, ImageFactory<?>> FACTORIES = new HashMap<>();

    private final String startURL;
    private final CloseableHttpClient httpClient;
    private final URLUtility urlUtils;
    private final Validator imageValidator;
    private final DomainResolver domainResolver;
    private final Comparator comparator;
    private final Extractor extractor;

    public HtmlParser(String startURL, CloseableHttpClient httpClient) {
        FACTORIES.put("gif", new GIFImageFactory());
        FACTORIES.put("jpeg", new JPGImageFactory());
        FACTORIES.put("jpg", new JPGImageFactory());
        FACTORIES.put("png", new PNGImageFactory());
        FACTORIES.put("svg", new SVGImageFactory());
        FACTORIES.put("webp", new JPGImageFactory());

        this.startURL = startURL;
        this.httpClient = httpClient;

        this.urlUtils = new URLUtility();
        this.imageValidator = new ImageValidator();
        this.domainResolver = new DomainResolver();
        this.comparator = new DomainComparator();
        this.extractor = new ExtensionExtractor();
    }

    @Override
    public Set<Image> getImages(String url) {
        Document document = getDocument(url);
        Set<Image> images = new HashSet<>();
        AtomicInteger id = new AtomicInteger(1000);

        if (document != null) {
            Elements imgTags = document.select("img[src]");

            images = imgTags.stream()
                    .map(tag -> {
                        String extension = extractor.getExtension(tag);
                        boolean isValid = imageValidator.validate(extension);
                        return isValid ? FACTORIES.get(extension).createImage(id.getAndIncrement(), tag) : null;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
        }

        return images;
    }

    @Override
    public Set<String> getUrls(String url) {
        Document document = getDocument(url);
        Set<String> urls = new HashSet<>();

        if (document != null) {
            Elements anchorTags = document.select("a[href]");

            urls = anchorTags.stream()
                    .map(tag -> tag.absUrl("href").trim().replaceAll("\\s", "%20"))
                    .filter(urlUtils::validate)
                    .filter(tag -> {
                        String domain = domainResolver.getDomain(startURL);
                        return comparator.isSameDomain(tag, domain);
                    })
                    .map(urlUtils::trimURL)
                    .collect(Collectors.toSet());
        }

        return urls;
    }

    @Override
    public Document getDocument(String url) {
        HttpGet httpGet = new HttpGet(url);

        // Execute the HTTP GET request
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();

            // Check if the response contains data
            if (entity != null) {
                String html = EntityUtils.toString(entity);
                return Jsoup.parse(html, httpGet.getUri().toString(), Parser.htmlParser());
            }
        } catch (IOException | URISyntaxException | ParseException e) {
           LOGGER.error(e.getMessage());
        }

        // Return null if there was no valid response or if there was an error
        return null;
    }

}