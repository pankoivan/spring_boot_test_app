package org.example.spring_boot_test_app.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException {

    public InputValidationException() {

    }

    public InputValidationException(String msg) {
        super(msg);
    }

    public InputValidationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
