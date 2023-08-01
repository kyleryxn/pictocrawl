package com.pictocrawl.util;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("URLUtility Tests")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class URLUtilityTest {
    private String url;
    private URLUtility urlUtility;

    @BeforeAll
    void setup() {
        urlUtility = new URLUtility();
    }

    @Nested
    @DisplayName("Method: trimURL")
    class TrimURL {

        @DisplayName("Test: Return Equal for Trimmed URL")
        @Test
        void givenUrlWithPound_whenTrimming_thenReturnTrimmedUrl() {
            url = "https://example.com/#contact";
            String expected = "https://example.com/";
            String actual = urlUtility.trimURL(url);

            assertEquals(expected, actual);
        }

        @DisplayName("Test: Return Equal for Original URL")
        @Test
        void givenUrlWithoutPound_whenTrimming_thenReturnSameUrl() {
            url = "https://example.com/";
            String actual = urlUtility.trimURL(url);

            assertEquals(url, actual);
        }

        @DisplayName("Test: Throw IllegalArgumentException for Null and Empty Values")
        @ParameterizedTest(name = "Test {index}: {0}")
        @NullAndEmptySource
        void givenNullEmptyValue_whenTrimming_thenThrowIllegalArgumentException(String url) {
            assertThrows(IllegalArgumentException.class, () -> urlUtility.trimURL(url));
        }

    }

    @Nested
    @DisplayName("Method: validate")
    class Validate {

        @DisplayName("Test: Return True for Valid URL")
        @Test
        void givenUrl_whenValidating_thenReturnValidUrl() {
            url = "https://example.com/";
            boolean result = urlUtility.validate(url);

            assertTrue(result);
        }

        @DisplayName("Test: Return False for Invalid URL")
        @ParameterizedTest(name = "Test {index}: {0}")
        @ValueSource(strings = {"#", "javascript:void(0)", "ftp:123.5467.4", "https://example.com/sample.pdf", "mailto:example@test.com"})
        void givenUrl_whenValidating_thenReturnInvalidUrl(String url) {
            boolean result = urlUtility.validate(url);
            assertFalse(result);
        }

        @DisplayName("Test: Throw IllegalArgumentException for Null and Empty Values")
        @ParameterizedTest(name = "Test {index}: {0}")
        @NullAndEmptySource
        void givenNullEmptyValue_whenValidating_thenThrowIllegalArgumentException(String url) {
            assertThrows(IllegalArgumentException.class, () -> urlUtility.validate(url));
        }

    }

}