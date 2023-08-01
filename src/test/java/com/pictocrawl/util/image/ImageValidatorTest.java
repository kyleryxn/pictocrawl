package com.pictocrawl.util.image;

import com.pictocrawl.util.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("ImageValidator Tests")
class ImageValidatorTest {
    private Validator validator;

    @BeforeEach
    void setup() {
        validator = new ImageValidator();
    }

    @DisplayName("Test: Return True for Valid Image Extension")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg", "webp"})
    void givenImageExtension_whenValidating_thenReturnValidImage(String extension) {
        boolean result = validator.validate(extension);
        assertTrue(result);
    }

    @DisplayName("Test: Return False for Invalid Image Extension")
    @ParameterizedTest(name = "Test {index}: {0}")
    @ValueSource(strings = {"bmp", "ico", "tiff"})
    void givenImageExtension_whenValidating_thenReturnInvalidImage(String extension) {
        boolean result = validator.validate(extension);
        assertFalse(result);
    }

    @DisplayName("Test: Throw IllegalArgumentException for Null and Empty Values")
    @ParameterizedTest(name = "Test {index}: {0}")
    @NullAndEmptySource
    void givenNullEmptyValue_whenValidating_thenThrowIllegalArgumentException(String extension) {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(extension));
    }

}