package com.pictocrawl.factory;

import com.pictocrawl.factory.image.GIFImageFactory;
import com.pictocrawl.factory.image.ImageFactory;
import com.pictocrawl.model.image.GIFImage;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GIFImageFactory Tests")
class GIFImageFactoryTest {
    private GIFImage image;
    private ImageFactory<GIFImage> factory;
    private Element element;

    @BeforeEach
    void setup() {
        String src = "https://example.com/images/test-image.gif";
        String alt = "Test Image";

        factory = new GIFImageFactory();
        element = new Element(Tag.valueOf("img"), "");
        element.attr("src", src);
        element.attr("alt", alt);
    }

    @DisplayName("Test: Return Equal for Image ID")
    @Test
    void givenImageId_whenCreatingImage_thenReturnGIFImageId() {
        int id = 2;
        image = factory.createImage(id, element);

        assertEquals(id, image.getId());
    }

    @DisplayName("Test: Return Equal for Image Name")
    @Test
    void givenImageName_whenCreatingImage_thenReturnGIFImageName() {
        image = factory.createImage(0, element);
        assertEquals("Test Image", image.getName());
    }

    @DisplayName("Test: Return True for Logo")
    @Test
    void givenLogoAttributes_whenCreatingImage_thenReturnIsLogo() {
        element.attr("alt", "company-logo");
        image = factory.createImage(0, element);

        assertTrue(image.isLogo());
    }

    @DisplayName("Test: Return False for Logo")
    @Test
    void givenNonLogoAttributes_whenCreatingImage_thenReturnNotLogo() {
        image = factory.createImage(0, element);
        assertFalse(image.isLogo());
    }

    @DisplayName("Test: Return Equal for Image URL")
    @Test
    void givenImageUrl_whenCreatingImage_thenReturnGIFImageUrl() {
        image = factory.createImage(0, element);
        assertEquals("https://example.com/images/test-image.gif", image.getUrl());
    }

}