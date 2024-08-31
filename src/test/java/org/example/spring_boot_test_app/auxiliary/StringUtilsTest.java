package org.example.spring_boot_test_app.auxiliary;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    void testIfNullThenEmpty_ifNullStringThenEmptyString() {
        assertThat(StringUtils.ifNullThenEmpty(null)).isEmpty();
    }

    @Test
    void testIfNullThenEmpty_ifStringThenString() {
        assertThat(StringUtils.ifNullThenEmpty("Byb")).isEqualTo("Byb");
    }

}
