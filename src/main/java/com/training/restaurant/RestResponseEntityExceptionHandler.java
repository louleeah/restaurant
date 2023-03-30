package com.training.restaurant;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException re, WebRequest req) {
        String responseBody = "Exception caught (" + re.getClass() + "), " + re.getMessage();
        return handleExceptionInternal(re, responseBody, new HttpHeaders(), HttpStatus.CONFLICT, req);
    }
}
