package com.pictocrawl.factory;

import com.pictocrawl.factory.image.SVGImageFactory;
import com.pictocrawl.factory.image.ImageFactory;
import com.pictocrawl.model.image.SVGImage;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SVGImageFactory Tests")
class SVGImageFactoryTest {
    private SVGImage image;
    private ImageFactory<SVGImage> factory;
    private Element element;

    @BeforeEach
    void setup() {
        String src = "https://example.com/images/test-image.svg";
        String alt = "Test Image";

        factory = new SVGImageFactory();
        element = new Element(Tag.valueOf("img"), "");
        element.attr("src", src);
        element.attr("alt", alt);
    }

    @DisplayName("Test: Return Equal for Image ID")
    @Test
    void givenImageId_whenCreatingImage_thenReturnSVGImageId() {
        int id = 2;
        image = factory.createImage(id, element, 0);

        assertEquals(id, image.getId());
    }

    @DisplayName("Test: Return Equal for Image Name")
    @Test
    void givenImageName_whenCreatingImage_thenReturnSVGImageName() {
        image = factory.createImage(0, element, 0);
        assertEquals("Test Image", image.getName());
    }

    @DisplayName("Test: Return True for Logo")
    @Test
    void givenLogoAttributes_whenCreatingImage_thenReturnIsLogo() {
        element.attr("alt", "company-logo");
        image = factory.createImage(0, element, 0);

        assertTrue(image.isLogo());
    }

    @DisplayName("Test: Return False for Logo")
    @Test
    void givenNonLogoAttributes_whenCreatingImage_thenReturnNotLogo() {
        image = factory.createImage(0, element, 0);
        assertFalse(image.isLogo());
    }

    @DisplayName("Test: Return Equal for Image URL")
    @Test
    void givenImageUrl_whenCreatingImage_thenReturnSVGImageUrl() {
        image = factory.createImage(0, element, 0);
        assertEquals("https://example.com/images/test-image.svg", image.getUrl());
    }

}