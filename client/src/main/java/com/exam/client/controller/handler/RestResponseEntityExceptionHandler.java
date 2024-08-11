package com.exam.client.controller.handler;

import com.exam.client.exception.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<ErrorMessage> handleEntityNotFound(RuntimeException ex) {
        ErrorMessage apiError = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorMessage> handleDataIntegrityViolation(RuntimeException ex) {
        ErrorMessage apiError = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase());

        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
    }

}
