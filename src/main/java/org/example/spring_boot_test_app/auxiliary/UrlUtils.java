package org.example.spring_boot_test_app.auxiliary;

import lombok.experimental.UtilityClass;
import org.example.spring_boot_test_app.main.exceptions.UrlValidationException;

@UtilityClass
public final class UrlUtils {

    public static int parsePathId(String pathId) {
        int id;
        try {
            id = Integer.parseInt(pathId);
        } catch (NumberFormatException e) {
            throw new UrlValidationException("Некорректный id: \"%s\"".formatted(pathId));
        }
        if (id <= 0) {
            throw new UrlValidationException("Некорректный id: \"%s\"".formatted(pathId));
        }
        return id;
    }

}
