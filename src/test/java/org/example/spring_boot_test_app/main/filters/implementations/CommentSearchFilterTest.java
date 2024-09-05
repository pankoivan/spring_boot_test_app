package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.Comment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class CommentSearchFilterTest {

    private final Comment comment = Comment
            .builder()
            .text("CommentText")
            .build();

    private final CommentSearchFilter filter = new CommentSearchFilter();

    @Test
    void testMatches_trueCase() {
        assertThat(filter.matches(comment, "|commenttext|")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(comment, "test")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> filter.matches(null, "AnyString"));
    }

}
