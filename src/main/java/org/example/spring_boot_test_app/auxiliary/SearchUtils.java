package org.example.spring_boot_test_app.auxiliary;

import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.BiFunction;

@UtilityClass
public final class SearchUtils {

    public static boolean contains(String sourceString, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return sourceString.contains(searchString);
    }

    public static boolean contains(List<String> sourceStrings, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return withListOfSourceStrings(sourceStrings, searchString, SearchUtils::contains);
    }

    public static boolean containsIgnoreCase(String sourceString, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return contains(sourceString.toLowerCase(), searchString.toLowerCase());
    }

    public static boolean containsIgnoreCase(List<String> sourceStrings, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return withListOfSourceStrings(sourceStrings, searchString, SearchUtils::containsIgnoreCase);
    }

    public static boolean bidirectionalContains(String sourceString, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return contains(sourceString, searchString) || contains(searchString, sourceString);
    }

    public static boolean bidirectionalContains(List<String> sourceStrings, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return withListOfSourceStrings(sourceStrings, searchString, SearchUtils::bidirectionalContains);
    }

    public static boolean biDirectionalContainsIgnoreCase(String sourceString, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return bidirectionalContains(sourceString.toLowerCase(), searchString.toLowerCase());
    }

    public static boolean biDirectionalContainsIgnoreCase(List<String> sourceStrings, String searchString) {
        searchString = StringUtils.ifNullThenEmpty(searchString);
        return withListOfSourceStrings(sourceStrings, searchString, SearchUtils::biDirectionalContainsIgnoreCase);
    }

    private static boolean withListOfSourceStrings(
            List<String> sourceStrings,
            String searchString,
            BiFunction<String, String, Boolean> searchFunction
    ) {
        for (String sourceString : sourceStrings) {
            if (searchFunction.apply(sourceString, searchString)) {
                return true;
            }
        }
        return false;
    }

}
