package org.example.spring_boot_test_app.auxiliary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SearchUtilsTest {

    // contains

    @Test
    void testContains_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.contains("|Byb|", "Byb")).isTrue(),
                () -> assertThat(SearchUtils.contains("|Byb|", "")).isTrue(),
                () -> assertThat(SearchUtils.contains("|Byb|", null)).isTrue()
        );
    }

    @Test
    void testContains_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.contains("|Byb|", "pop")).isFalse(),
                () -> assertThat(SearchUtils.contains("|Byb|", "byb")).isFalse(),
                () -> assertThat(SearchUtils.contains("Byb", "|Byb|")).isFalse()
        );
    }

    @Test
    void testContains_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.contains((String) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // containsIgnoreCase

    @Test
    void testContainsIgnoreCase_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.containsIgnoreCase("|Byb|", "Byb")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase("|Byb|", "byb")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase("Byb", "")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase("Byb", null)).isTrue()
        );
    }

    @Test
    void testContainsIgnoreCase_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.containsIgnoreCase("|Byb|", "pop")).isFalse(),
                () -> assertThat(SearchUtils.containsIgnoreCase("Byb", "|Byb|")).isFalse()
        );
    }

    @Test
    void testContainsIgnoreCase_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.containsIgnoreCase((String) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // biDirectionalContains

    @Test
    void testBiDirectionalContains_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.bidirectionalContains("|Byb|", "Byb")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains("Byb", "|Byb|")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains("Byb", "")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains("Byb", null)).isTrue()
        );
    }

    @Test
    void testBiDirectionalContains_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.bidirectionalContains("|Byb|", "pop")).isFalse(),
                () -> assertThat(SearchUtils.bidirectionalContains("|Byb|", "byb")).isFalse()
        );
    }

    @Test
    void testBiDirectionalContains_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.bidirectionalContains((String) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // biDirectionalContainsIgnoreCase

    @Test
    void testBiDirectionalContainsIgnoreCase_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("|Byb|", "Byb")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("Byb", "|Byb|")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("|Byb|", "byb")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("byb", "|Byb|")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("Byb", "")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("Byb", null)).isTrue()
        );
    }

    @Test
    void testBiDirectionalContainsIgnoreCase_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase("|Byb|", "pop")).isFalse()
        );
    }

    @Test
    void testBiDirectionalContainsIgnoreCase_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.biDirectionalContainsIgnoreCase((String) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // listContains

    @Test
    void testListContains_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp")).isTrue(),
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "|Pyp|", "|Bob|"), "")).isTrue(),
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "|Pyp|", "|Bob|"), null)).isTrue()
        );
    }

    @Test
    void testListContains_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop")).isFalse(),
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp")).isFalse(),
                () -> assertThat(SearchUtils.contains(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")).isFalse()
        );
    }

    @Test
    void testListContains_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.contains((List<String>) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // listContainsIgnoreCase

    @Test
    void testListContainsIgnoreCase_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "")).isTrue(),
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), null)).isTrue()
        );
    }

    @Test
    void testListContainsIgnoreCase_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop")).isFalse(),
                () -> assertThat(SearchUtils.containsIgnoreCase(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")).isFalse()
        );
    }

    @Test
    void testListContainsIgnoreCase_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.containsIgnoreCase((List<String>) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // listBiDirectionalContains

    @Test
    void testListBiDirectionalContains_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "|Pyp|", "|Bob|"), "")).isTrue(),
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "|Pyp|", "|Bob|"), null)).isTrue()
        );
    }

    @Test
    void testListBiDirectionalContains_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop")).isFalse(),
                () -> assertThat(SearchUtils.bidirectionalContains(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp")).isFalse()
        );
    }

    @Test
    void testListBiDirectionalContains_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.bidirectionalContains((List<String>) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

    // listBiDirectionalContainsIgnoreCase

    @Test
    void testListBiDirectionalContainsIgnoreCase_trueCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "pyp", "|Bob|"), "|Pyp|")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), "")).isTrue(),
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("|Byb|", "|Pyp|", "|Bob|"), null)).isTrue()
        );
    }

    @Test
    void testListBiDirectionalContainsIgnoreCase_falseCase() {
        Assertions.assertAll(
                () -> assertThat(SearchUtils.biDirectionalContainsIgnoreCase(List.of("Byb", "Pyp", "Bob"), "pop")).isFalse()
        );
    }

    @Test
    void testListBiDirectionalContainsIgnoreCase_throwsCase() {
        assertThatThrownBy(() -> SearchUtils.biDirectionalContainsIgnoreCase((List<String>) null, "Byb")).isInstanceOf(NullPointerException.class);
    }

}
