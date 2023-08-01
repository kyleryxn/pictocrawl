package com.pictocrawl.util.image;

import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ExtensionExtractor Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExtensionExtractorTest {
    private String url;
    private Extractor extractor;

    @BeforeAll
    void setup() {
        url = "https://example.com/image/test-image.png";
        extractor = new ExtensionExtractor();
    }

    @Nested
    @DisplayName("Method: getExtension (String parameter)")
    class GetExtensionString {
        @DisplayName("Test: Return Equal for URL with Extension")
        @Test
        void givenURL_whenExtracting_thenReturnExtensionDetected() {
            String expected = "png";
            String actual = extractor.getExtension(url);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Equal for URL with Query Parameters")
        @Test
        void givenURLWithQueryParameters_whenExtracting_thenReturnExtensionDetected() {
            String expected = "png";
            String actual = extractor.getExtension(url + "?={parameter}");

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Not Equal for URL without Extension")
        @Test
        void givenValidURLWithoutExtension_whenExtracting_thenReturnNoExtensionDetected() {
            String expected = "No extension detected";
            String noImageExtension = url.substring(0, url.indexOf(".png"));
            String actual = extractor.getExtension(noImageExtension);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Equal for Empty Value")
        @ParameterizedTest(name = "Test: {index}: {0}")
        @EmptySource
        void givenEmptyValue_whenExtracting_thenReturnNoExtensionDetected(String url) {
            String expected = "No extension detected";
            String actual = extractor.getExtension(url);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Throw IllegalArgumentException for Null Value")
        @ParameterizedTest(name = "Test {index}: {0}")
        @NullSource
        void givenNullEmptyValue_whenExtracting_thenThrowIllegalArgumentException(String url) {
            assertThrows(IllegalArgumentException.class, () -> extractor.getExtension(url));
        }

    }

    @Nested
    @DisplayName("Method: getExtension (Jsoup element parameter)")
    class GetExtensionElement {

        @DisplayName("Test: Return Equal for URL with Extension")
        @Test
        void givenJsoupElement_whenExtracting_thenReturnExtensionDetected() {
            Element element = new Element(Tag.valueOf("img").getName());
            element.attr("src", url);

            String expected = "png";
            String actual = extractor.getExtension(element);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Equal for URL with Query Parameters")
        @Test
        void givenJsoupElement_whenExtracting_thenReturnNoExtensionDetected() {
            Element element = new Element(Tag.valueOf("img").getName());
            element.attr("src", url + "?={parameter}");

            String expected = "png";
            String actual = extractor.getExtension(element);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Equal for Empty Value")
        @ParameterizedTest(name = "Test: {index}: {0}")
        @EmptySource
        void givenEmptyValue_whenExtracting_thenReturnNoExtensionDetected(String url) {
            Element element = new Element(Tag.valueOf("img").getName());
            element.attr("src", url);

            String expected = "No extension detected";
            String actual = extractor.getExtension(url);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Throw IllegalArgumentException for Null Value")
        @ParameterizedTest(name = "Test {index}: {0}")
        @NullSource
        void givenNullJsoupElement_whenExtracting_thenThrowException(Element element) {
            assertThrows(IllegalArgumentException.class, () -> extractor.getExtension(element));
        }

    }

}