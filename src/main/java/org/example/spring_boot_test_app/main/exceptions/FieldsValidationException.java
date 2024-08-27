package org.example.spring_boot_test_app.main.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class FieldsValidationException extends InputValidationException {

    private final List<FieldError> fieldErrors;

    public FieldsValidationException(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public FieldsValidationException(List<FieldError> fieldErrors, String msg) {
        super(msg);
        this.fieldErrors = fieldErrors;
    }

    public FieldsValidationException(List<FieldError> fieldErrors, String msg, Throwable cause) {
        super(msg, cause);
        this.fieldErrors = fieldErrors;
    }

}
