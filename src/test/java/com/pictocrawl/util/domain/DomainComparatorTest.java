package com.pictocrawl.util.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DomainComparator Tests")
class DomainComparatorTest {
    private DomainComparator comparator;
    private String url;

    @BeforeEach
    void setUp() {
        comparator = new DomainComparator();
        url = "https://wecodeucate.org/";
    }

    @DisplayName("Test: Return True for Same Domain")
    @Test
    void givenSameDomain_whenComparing_thenReturnTrue() {
        boolean result = comparator.isSameDomain(url, "wecodeucate.org");
        assertTrue(result);
    }

    @DisplayName("Test: Return False for Different Domains")
    @Test
    void givenDifferentDomain_whenComparing_thenReturnFalse() {
        boolean result = comparator.isSameDomain(url, "www.wecodeucate.org");
        assertFalse(result);
    }

    @DisplayName("Test: Throw IllegalArgumentException for Null and Empty Values")
    @ParameterizedTest(name = "Test {index}: {0}")
    @NullAndEmptySource
    void givenNullEmptyValue_whenComparing_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> comparator.isSameDomain(url, "example.com"));
    }

    @DisplayName("Test: Throw IllegalArgumentException for Whitespace Value")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {" "})
    void givenWhitespaceValue_whenComparing_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> comparator.isSameDomain(url, "example.com"));
    }

    @DisplayName("Test: Throw IllegalArgumentException for Invalid Link Starters and Extensions")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {"#", "javascript:", "mailto:", "data:", "ftp:", ".pdf"})
    void givenInvalidLinks_whenComparing_thenThrowIllegalArgumentException(String url) {
        assertThrows(IllegalArgumentException.class, () -> comparator.isSameDomain(url, "example.com"));
    }

}