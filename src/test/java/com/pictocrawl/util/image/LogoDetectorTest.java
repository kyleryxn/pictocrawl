package com.pictocrawl.util.image;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;


class LogoDetectorTest {
    private Element element;
    private String url;
    private Detector detector;

    @BeforeEach
    void setup() {
        element = new Element(Tag.valueOf("img").getName());
        url = "https://example.com/images/test-image.jpg";
        detector = new LogoDetector();
    }

    @DisplayName("Test: Return True for Logo Detected (Logo)")
    @Test
    void givenJsoupElement_whenDetecting_thenReturnLogoDetected() {
        element.attr("src", url);
        element.attr("alt", "Company Logo");
        boolean result = detector.isLogo(element);

        assertTrue(result);
    }

    @DisplayName("Test: Return True for Logo Detected (Brand)")
    @Test
    void givenJsoupElement_whenDetecting_thenReturnBrandDetected() {
        element.attr("src", url);
        element.attr("alt", "Company Brand");
        boolean result = detector.isLogo(element);

        assertTrue(result);
    }

    @DisplayName("Test: Return False for No Logo Detected")
    @Test
    void givenJsoupElement_whenDetecting_thenReturnLogoNotDetected() {
        element.attr("src", url);
        element.attr("alt", "Company");
        boolean result = detector.isLogo(element);

        assertFalse(result);
    }

    @DisplayName("Test: Throw IllegalArgumentException for Null Value")
    @ParameterizedTest(name = "Test {index}: {0}")
    @NullSource
    void givenNullValue_whenDetecting_thenThrowIllegalArgumentException(Element element) {
        assertThrows(IllegalArgumentException.class, () -> detector.isLogo(element));
    }

}