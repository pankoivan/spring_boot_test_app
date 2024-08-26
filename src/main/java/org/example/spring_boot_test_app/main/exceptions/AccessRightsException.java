package org.example.spring_boot_test_app.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessRightsException extends RuntimeException {

    public AccessRightsException() {

    }

    public AccessRightsException(String msg) {
        super(msg);
    }

    public AccessRightsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
