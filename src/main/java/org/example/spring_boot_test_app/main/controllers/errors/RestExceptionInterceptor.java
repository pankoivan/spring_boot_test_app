package org.example.spring_boot_test_app.main.controllers.errors;

import org.example.spring_boot_test_app.main.controllers.errors.entities.FieldsValidationExceptionResponseEntity;
import org.example.spring_boot_test_app.main.controllers.errors.entities.NoFieldsValidationExceptionResponseEntity;
import org.example.spring_boot_test_app.main.exceptions.AccessRightsException;
import org.example.spring_boot_test_app.main.exceptions.EntityNoFoundException;
import org.example.spring_boot_test_app.main.exceptions.FieldsValidationException;
import org.example.spring_boot_test_app.main.exceptions.UrlValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@PreAuthorize("permitAll()")
public class RestExceptionInterceptor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            UrlValidationException.class,
            ServerWebInputException.class,
            HttpClientErrorException.BadRequest.class
    })
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> badRequest(Exception e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FieldsValidationException.class)
    public ResponseEntity<FieldsValidationExceptionResponseEntity> badRequest(FieldsValidationException e) {
        FieldsValidationExceptionResponseEntity json = new FieldsValidationExceptionResponseEntity(e.getFieldErrors());
        return new ResponseEntity<>(json, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> unauthorized(HttpClientErrorException.Unauthorized e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({
            AccessRightsException.class,
            AccessDeniedException.class,
            HttpClientErrorException.Forbidden.class,
    })
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> forbidden(Exception e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            EntityNoFoundException.class,
            NoHandlerFoundException.class,
            NoResourceFoundException.class,
            HttpClientErrorException.NotFound.class,
    })
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> notFound(Exception e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler({
            HttpRequestMethodNotSupportedException.class,
            HttpClientErrorException.MethodNotAllowed.class
    })
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> methodNotAllowed(Exception e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<NoFieldsValidationExceptionResponseEntity> internalServer(Throwable e) {
        NoFieldsValidationExceptionResponseEntity json = new NoFieldsValidationExceptionResponseEntity(e.getMessage());
        return new ResponseEntity<>(json, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
