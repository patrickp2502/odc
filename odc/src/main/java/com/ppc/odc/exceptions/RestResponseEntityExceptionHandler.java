package com.ppc.odc.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    //todo test execption handling
    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<Object> handleJPAExceptions(RuntimeException exception,
                                                         WebRequest request) {
        String bodyOfResponse = exception.getMessage();
        return handleExceptionInternal(
                exception,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.NOT_FOUND,
                request);




    }
}
