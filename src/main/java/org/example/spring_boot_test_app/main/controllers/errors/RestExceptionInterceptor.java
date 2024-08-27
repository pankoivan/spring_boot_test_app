package org.example.spring_boot_test_app.main.controllers.errors;

import org.example.spring_boot_test_app.main.controllers.entities.FieldsValidationExceptionResponseEntity;
import org.example.spring_boot_test_app.main.controllers.entities.NoFieldsValidationExceptionResponseEntity;
import org.example.spring_boot_test_app.main.exceptions.FieldsValidationException;
import org.example.spring_boot_test_app.main.exceptions.InputValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@PreAuthorize("permitAll()")
public class RestExceptionInterceptor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> badRequest(InputValidationException e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FieldsValidationException.class)
    public ResponseEntity<FieldsValidationExceptionResponseEntity> badRequest(FieldsValidationException e) {
        FieldsValidationExceptionResponseEntity json = new FieldsValidationExceptionResponseEntity(e.getFieldErrors());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> internalServer(Throwable e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
