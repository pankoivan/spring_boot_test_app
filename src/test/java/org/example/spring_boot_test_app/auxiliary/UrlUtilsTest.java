package org.example.spring_boot_test_app.auxiliary;

import org.example.spring_boot_test_app.main.exceptions.UrlValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UrlUtilsTest {

    @ParameterizedTest
    @ValueSource(strings = {"142857", "+142857", "0142857", "00000000000000000000000000000000000142857"})
    void testParsePathId_successCase(String pathId) {
        assertThat(UrlUtils.parsePathId(pathId)).isEqualTo(142857);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"not a number", "-142857", "0"})
    void testParsePathId_throwsCase(String pathId) {
        assertThatThrownBy(() -> UrlUtils.parsePathId(pathId))
                .isInstanceOf(UrlValidationException.class)
                .hasMessage("Некорректный id: \"%s\"".formatted(pathId));
    }

}
