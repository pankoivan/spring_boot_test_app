package org.example.spring_boot_test_app.main.entities.common;

import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AbstractBaseEntityTest {

    public static Stream<Arguments> testGetFormattedEditingDateSuccessCaseProvider() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(LocalDateTime.of(2020, 5, 6, 14, 41), "06 мая 2020 в 14:41:00")
        );
    }

    @Test
    void testGetFormattedCreationDate_successCase() {
        AbstractBaseEntity product = new Product();
        product.setCreationDate(LocalDateTime.of(2020, 5, 6, 14, 41));
        assertThat(product.getFormattedCreationDate()).isEqualTo("06 мая 2020 в 14:41:00");
    }

    @Test
    void testGetFormattedCreationDate_throwsCase() {
        assertThatThrownBy(() -> new Product().getFormattedCreationDate()).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @MethodSource("testGetFormattedEditingDateSuccessCaseProvider")
    void testGetFormattedEditingDate__successCase(LocalDateTime editingDate, String formattedEditingDate) {
        AbstractBaseEntity product = new Product();
        product.setEditingDate(editingDate);
        assertThat(product.getFormattedEditingDate()).isEqualTo(formattedEditingDate);
    }

}
