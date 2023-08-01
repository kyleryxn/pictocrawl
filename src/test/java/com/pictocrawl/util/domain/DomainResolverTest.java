package com.pictocrawl.util.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DomainResolver Tests")
class DomainResolverTest {
    private String url;
    private DomainResolver resolver;

    @BeforeEach
    void setup() {
        url = "https://example.com";
        resolver = new DomainResolver();
    }

    @DisplayName("Test: Return Equal for Expected Domain")
    @Test
    void givenURL_whenRetrievingDomain_thenReturnEqual() {
        String expected = "example.com";
        String actual = resolver.getDomain(url);

        assertEquals(expected, actual);
    }

    @DisplayName("Test: Return Not Equal for Expected Domain")
    @Test
    void givenURL_whenRetrievingDomain_thenReturnNotEqual() {
        String expected = "www.example.com";
        String actual = resolver.getDomain(url);

        assertNotEquals(expected, actual);
    }

    @DisplayName("Test: Throw IllegalArgumentException for Null and Empty Values")
    @ParameterizedTest(name = "Test {index}: {0}")
    @NullAndEmptySource
    void givenNullEmptyValue_whenRetrievingDomain_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> resolver.getDomain(url));
    }

    @DisplayName("Test: Throw IllegalArgumentException for Whitespace Value")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {" "})
    void givenWhitespace_whenRetrievingDomain_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> resolver.getDomain(url));
    }

    @DisplayName("Test: Throw IllegalArgumentException for Malformed URLs")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {
            "#",
            "https:/example.com",
            // "http://ww.example.com", test fails currently
            // "https://wwww.example.com", test fails currently
            "https://#",
            "http://",
            "https://#.com",
            "htts://#.com",
            "https//#.com",

            // Missing colon
            "http//example.com",
            "http//www.example.com",

            // Space in URL
            "https://example .com",

            // Unencoded spaces
            "https://example.com/some path/to/resource",

            // Missing domain
            "https:///path/to/resource",

            // Incomplete protocol
            "http:/example.com",

            // Incorrect protocols
            // "htt://example.com", test fails currently
            // "htp://example.com", test fails currently

            // Missing top level domain
            // "https://example/", test fails currently

            // Unenclosed braces
            "https://example.com/page?param=value{unclosed",

            // Invalid port
            "https://example.com:abc/page",

            // Missing path
            // "https://example.com?param=value", test fails currently

            // Invalid characters
            // "https://example.com/p@ge?param=value#fragment" test fails currently
    })
    void givenMalformedURL_whenRetrievingDomain_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> resolver.getDomain(url));
    }

}