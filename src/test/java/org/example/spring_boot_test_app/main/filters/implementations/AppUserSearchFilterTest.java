package org.example.spring_boot_test_app.main.filters.implementations;

import org.example.spring_boot_test_app.main.entities.AppUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AppUserSearchFilterTest {

    private final AppUser appUser1 = AppUser
            .builder()
            .username("testMail@mail")
            .build();

    private final AppUser appUser2 = AppUser
            .builder()
            .username("oneMoreTestMail@mail")
            .build();

    private final AppUser appUser3 = AppUser
            .builder()
            .username("someMail@yandex")
            .build();

    @Spy
    private AppUserSearchFilter filter;

    @Test
    void testMatches_trueCase() {
        assertThat(filter.matches(appUser1, "|testmail@mail|")).isTrue();
    }

    @Test
    void testMatches_falseCase() {
        assertThat(filter.matches(appUser3, "test")).isFalse();
    }

    @Test
    void testMatches_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> filter.matches(null, "AnyString"));
    }

    @Test
    void testSearched_emptySourceListSuccessCase() {
        assertThat(filter.searched(List.of(), "AnyString")).isEqualTo(new LinkedHashSet<>());
    }

    @Test
    void testSearched_notFoundSuccessCase() {
        doReturn(false).when(filter).matches(appUser1, "test");
        doReturn(false).when(filter).matches(appUser2, "test");
        doReturn(false).when(filter).matches(appUser3, "test");
        assertThat(filter.searched(List.of(appUser1, appUser2, appUser3), "test")).isEqualTo(new LinkedHashSet<>());
    }

    @Test
    void testSearched_foundSuccessCase() {
        doReturn(true).when(filter).matches(appUser1, "test");
        doReturn(true).when(filter).matches(appUser2, "test");
        doReturn(false).when(filter).matches(appUser3, "test");
        assertThat(filter.searched(List.of(appUser1, appUser2, appUser3), "test")).isEqualTo(new LinkedHashSet<>(List.of(appUser1, appUser2)));
    }

    @Test
    void testSearched_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> filter.searched(null, "AnyString"));
    }

}
