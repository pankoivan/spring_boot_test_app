package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class TagSearchFilterTest {

    private final Tag tag = Tag
            .builder()
            .name("TagName")
            .build();

    private final TagSearchFilter filter = new TagSearchFilter();

    @Test
    void testMatches_trueCase() {
        assertThat(filter.matches(tag, "|tagname|")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(tag, "test")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> filter.matches(null, "AnyString"));
    }

}
