package org.example.spring_boot_test_app.auxiliary;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public final class DateTimeFormatConstants {

    public static final DateTimeFormatter DAY_MONTH_YEAR_AT_HOUR_MINUTE_SECOND =
            DateTimeFormatter.ofPattern("dd MMM yyyy в HH:mm:ss", Locale.of("ru"));

}
