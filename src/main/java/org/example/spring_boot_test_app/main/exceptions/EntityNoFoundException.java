package org.example.spring_boot_test_app.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNoFoundException extends RuntimeException {

    public EntityNoFoundException() {

    }

    public EntityNoFoundException(String msg) {
        super(msg);
    }

    public EntityNoFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
