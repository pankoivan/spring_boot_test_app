package org.example.spring_boot_test_app.main.entities.common;

import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class AbstractBaseEntityTest {

    static Comment commentWithId(Integer id) {
        return Comment.builder().id(id).build();
    }

    static Product productWithId(Integer id) {
        return Product.builder().id(id).build();
    }

    static Stream<Arguments> testEqualsTrueCaseProvider() {
        Comment comment1 = commentWithId(null);
        Comment comment2 = commentWithId(123);
        return Stream.of(
                Arguments.of(comment1, comment1),
                Arguments.of(comment2, comment2),
                Arguments.of(commentWithId(142857), commentWithId(142857))
        );
    }

    static Stream<Arguments> testEqualsFalseCaseProvider() {
        Comment comment1 = commentWithId(null);
        Comment comment2 = commentWithId(123);
        Comment comment3 = commentWithId(456);
        Product product1 = productWithId(null);
        Product product2 = productWithId(123);
        return Stream.of(
                Arguments.of(comment1, comment2),
                Arguments.of(comment2, comment3),
                Arguments.of(comment1, product1),
                Arguments.of(comment1, product2),
                Arguments.of(comment2, product1),
                Arguments.of(comment2, product2)
        );
    }

    static Stream<Arguments> testGetFormattedEditingDateSuccessCaseProvider() {
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
        assertThatNullPointerException().isThrownBy(() -> new Product().getFormattedCreationDate());
    }

    @ParameterizedTest
    @MethodSource("testGetFormattedEditingDateSuccessCaseProvider")
    void testGetFormattedEditingDate_successCase(LocalDateTime editingDate, String formattedEditingDate) {
        AbstractBaseEntity product = new Product();
        product.setEditingDate(editingDate);
        assertThat(product.getFormattedEditingDate()).isEqualTo(formattedEditingDate);
    }

    @ParameterizedTest
    @MethodSource("testEqualsTrueCaseProvider")
    void testEquals_trueCase(AbstractBaseEntity entity1, AbstractBaseEntity entity2) {
        assertThat(entity1.equals(entity2)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testEqualsFalseCaseProvider")
    void testEquals_falseCase(AbstractBaseEntity entity1, AbstractBaseEntity entity2) {
        assertThat(entity1.equals(entity2)).isFalse();
    }

    @Test
    void testHashCode_correctHashCode() {
        assertThat(new Product().hashCode()).isEqualTo(Product.class.hashCode());
    }

}
