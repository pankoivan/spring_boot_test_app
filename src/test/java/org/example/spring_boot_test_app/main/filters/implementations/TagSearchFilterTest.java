package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.example.spring_boot_test_app.main.entities.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TagSearchFilterTest {

    private final Tag tag = Tag
            .builder()
            .name("TagName")
            .author(AppUser.builder().username("author@mail").build())
            .build();

    private final TagSearchFilter filter = new TagSearchFilter();

    @Test
    void testMatches_trueCase() {
        assertThat(filter.matches(tag, "name")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(tag, "nane")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatThrownBy(() -> filter.matches(null, "AnyString")).isInstanceOf(NullPointerException.class);
    }

}
