package com.pictocrawl.factory;

import com.pictocrawl.factory.image.ImageFactory;
import com.pictocrawl.factory.image.PNGImageFactory;
import com.pictocrawl.model.image.PNGImage;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PNGImageFactory Tests")
class PNGImageFactoryTest {
    private PNGImage image;
    private ImageFactory<PNGImage> factory;
    private Element element;

    @BeforeEach
    void setup() {
        String src = "https://example.com/images/test-image.png";
        String alt = "Test Image";

        factory = new PNGImageFactory();
        element = new Element(Tag.valueOf("img"), "");
        element.attr("src", src);
        element.attr("alt", alt);
    }

    @DisplayName("Test: Return Equal for Image ID")
    @Test
    void givenImageId_whenCreatingImage_thenReturnPNGImageId() {
        int id = 2;
        image = factory.createImage(id, element, 0);

        assertEquals(id, image.getId());
    }

    @DisplayName("Test: Return Equal for Image Name")
    @Test
    void givenImageName_whenCreatingImage_thenReturnPNGImageName() {
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
    void givenImageUrl_whenCreatingImage_thenReturnPNGImageUrl() {
        image = factory.createImage(0, element, 0);
        assertEquals("https://example.com/images/test-image.png", image.getUrl());
    }

}