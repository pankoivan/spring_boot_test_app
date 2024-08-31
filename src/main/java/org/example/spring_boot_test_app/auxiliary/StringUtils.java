package org.example.spring_boot_test_app.auxiliary;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class StringUtils {

    public static String ifNullThenEmpty(String string) {
        return string == null ? "" : string;
    }

}
