package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Comment;
import org.example.spring_boot_test_app.main.entities.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommentSearchFilterTest {

    private final Comment comment = Comment
            .builder()
            .text("CommentText")
            .product(Product.builder().name("ProductName").build())
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final CommentSearchFilter filter = new CommentSearchFilter();

    @Test
    void testMatches_trueCase() {
        assertThat(filter.matches(comment, "text")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(comment, "test")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatThrownBy(() -> filter.matches(null, "AnyString")).isInstanceOf(NullPointerException.class);
    }

}
