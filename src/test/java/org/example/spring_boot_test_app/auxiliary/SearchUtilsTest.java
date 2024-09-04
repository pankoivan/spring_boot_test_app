package org.example.spring_boot_test_app.auxiliary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class SearchUtilsTest {

    // contains providers

    static Stream<Arguments> testContainsTrueCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "Byb"),
                Arguments.of("|Byb|", ""),
                Arguments.of("|Byb|", null)
        );
    }

    static Stream<Arguments> testContainsFalseCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "pop"),
                Arguments.of("|Byb|", "byb"),
                Arguments.of("Byb", "|Byb|")
        );
    }

    // containsIgnoreCase providers

    static Stream<Arguments> testContainsIgnoreCaseTrueCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "Byb"),
                Arguments.of("|Byb|", "byb"),
                Arguments.of("Byb", ""),
                Arguments.of("Byb", null)
        );
    }

    static Stream<Arguments> testContainsIgnoreCaseFalseCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "pop"),
                Arguments.of("Byb", "|Byb|")
        );
    }

    // biDirectionalContains providers

    static Stream<Arguments> testBiDirectionalContainsTrueCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "Byb"),
                Arguments.of("Byb", "|Byb|"),
                Arguments.of("Byb", ""),
                Arguments.of("Byb", null)
        );
    }

    static Stream<Arguments> testBiDirectionalContainsFalseCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "pop"),
                Arguments.of("|Byb|", "byb")
        );
    }

    // biDirectionalContainsIgnoreCase providers

    static Stream<Arguments> testBiDirectionalContainsIgnoreCaseTrueCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "Byb"),
                Arguments.of("Byb", "|Byb|"),
                Arguments.of("|Byb|", "byb"),
                Arguments.of("byb", "|Byb|"),
                Arguments.of("Byb", ""),
                Arguments.of("Byb", null)
        );
    }

    static Stream<Arguments> testBiDirectionalContainsIgnoreCaseFalseCaseProvider() {
        return Stream.of(
                Arguments.of("|Byb|", "pop")
        );
    }

    // listContains providers

    static Stream<Arguments> testListContainsTrueCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), ""),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), null)
        );
    }

    static Stream<Arguments> testListContainsFalseCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp"),
                Arguments.of(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")
        );
    }

    // listContainsIgnoreCase providers

    static Stream<Arguments> testListContainsIgnoreCaseTrueCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), ""),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), null)
        );
    }

    static Stream<Arguments> testListContainsIgnoreCaseFalseCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop"),
                Arguments.of(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|")
        );
    }

    // listBiDirectionalContains providers

    static Stream<Arguments> testListBiDirectionalContainsTrueCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp"),
                Arguments.of(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), ""),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), null)
        );
    }

    static Stream<Arguments> testListBiDirectionalContainsFalseCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp")
        );
    }

    // listBiDirectionalContainsIgnoreCase providers

    static Stream<Arguments> testListBiDirectionalContainsIgnoreCaseTrueCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "Pyp"),
                Arguments.of(List.of("|Byb|", "Pyp", "|Bob|"), "|Pyp|"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pyp"),
                Arguments.of(List.of("|Byb|", "pyp", "|Bob|"), "|Pyp|"),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), ""),
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), null)
        );
    }

    static Stream<Arguments> testListBiDirectionalContainsIgnoreCaseFalseCaseProvider() {
        return Stream.of(
                Arguments.of(List.of("|Byb|", "|Pyp|", "|Bob|"), "pop")
        );
    }

    // contains tests

    @ParameterizedTest
    @MethodSource("testContainsTrueCaseProvider")
    void testContains_trueCase(String sourceString, String searchString) {
        assertThat(SearchUtils.contains(sourceString, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testContainsFalseCaseProvider")
    void testContains_falseCase(String sourceString, String searchString) {
        assertThat(SearchUtils.contains(sourceString, searchString)).isFalse();
    }

    @Test
    void testContains_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.contains((String) null, "Byb"));
    }

    // containsIgnoreCase tests

    @ParameterizedTest
    @MethodSource("testContainsIgnoreCaseTrueCaseProvider")
    void testContainsIgnoreCase_trueCase(String sourceString, String searchString) {
        assertThat(SearchUtils.containsIgnoreCase(sourceString, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testContainsIgnoreCaseFalseCaseProvider")
    void testContainsIgnoreCase_falseCase(String sourceString, String searchString) {
        assertThat(SearchUtils.containsIgnoreCase(sourceString, searchString)).isFalse();
    }

    @Test
    void testContainsIgnoreCase_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.containsIgnoreCase((String) null, "Byb"));
    }

    // biDirectionalContains tests

    @ParameterizedTest
    @MethodSource("testBiDirectionalContainsTrueCaseProvider")
    void testBiDirectionalContains_trueCase(String sourceString, String searchString) {
        assertThat(SearchUtils.bidirectionalContains(sourceString, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testBiDirectionalContainsFalseCaseProvider")
    void testBiDirectionalContains_falseCase(String sourceString, String searchString) {
        assertThat(SearchUtils.bidirectionalContains(sourceString, searchString)).isFalse();
    }

    @Test
    void testBiDirectionalContains_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.bidirectionalContains((String) null, "Byb"));
    }

    // biDirectionalContainsIgnoreCase tests

    @ParameterizedTest
    @MethodSource("testBiDirectionalContainsIgnoreCaseTrueCaseProvider")
    void testBiDirectionalContainsIgnoreCase_trueCase(String sourceString, String searchString) {
        assertThat(SearchUtils.biDirectionalContainsIgnoreCase(sourceString, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testBiDirectionalContainsIgnoreCaseFalseCaseProvider")
    void testBiDirectionalContainsIgnoreCase_falseCase(String sourceString, String searchString) {
        assertThat(SearchUtils.biDirectionalContainsIgnoreCase(sourceString, searchString)).isFalse();
    }

    @Test
    void testBiDirectionalContainsIgnoreCase_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.biDirectionalContainsIgnoreCase((String) null, "Byb"));
    }

    // listContains tests

    @ParameterizedTest
    @MethodSource("testListContainsTrueCaseProvider")
    void testListContains_trueCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.contains(sourceStrings, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testListContainsFalseCaseProvider")
    void testListContains_falseCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.contains(sourceStrings, searchString)).isFalse();
    }

    @Test
    void testListContains_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.contains((List<String>) null, "Byb"));
    }

    // listContainsIgnoreCase tests

    @ParameterizedTest
    @MethodSource("testListContainsIgnoreCaseTrueCaseProvider")
    void testListContainsIgnoreCase_trueCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.containsIgnoreCase(sourceStrings, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testListContainsIgnoreCaseFalseCaseProvider")
    void testListContainsIgnoreCase_falseCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.containsIgnoreCase(sourceStrings, searchString)).isFalse();
    }

    @Test
    void testListContainsIgnoreCase_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.containsIgnoreCase((List<String>) null, "Byb"));
    }

    // listBiDirectionalContains tests

    @ParameterizedTest
    @MethodSource("testListBiDirectionalContainsTrueCaseProvider")
    void testListBiDirectionalContains_trueCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.bidirectionalContains(sourceStrings, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testListBiDirectionalContainsFalseCaseProvider")
    void testListBiDirectionalContains_falseCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.bidirectionalContains(sourceStrings, searchString)).isFalse();
    }

    @Test
    void testListBiDirectionalContains_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.bidirectionalContains((List<String>) null, "Byb"));
    }

    // listBiDirectionalContainsIgnoreCase tests

    @ParameterizedTest
    @MethodSource("testListBiDirectionalContainsIgnoreCaseTrueCaseProvider")
    void testListBiDirectionalContainsIgnoreCase_trueCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.biDirectionalContainsIgnoreCase(sourceStrings, searchString)).isTrue();
    }

    @ParameterizedTest
    @MethodSource("testListBiDirectionalContainsIgnoreCaseFalseCaseProvider")
    void testListBiDirectionalContainsIgnoreCase_falseCase(List<String> sourceStrings, String searchString) {
        assertThat(SearchUtils.biDirectionalContainsIgnoreCase(sourceStrings, searchString)).isFalse();
    }

    @Test
    void testListBiDirectionalContainsIgnoreCase_throwsCase() {
        assertThatNullPointerException().isThrownBy(() -> SearchUtils.biDirectionalContainsIgnoreCase((List<String>) null, "Byb"));
    }

}
