package com.stepup.consumer.web;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * The {@code ErrorHandler} class is a Spring RestControllerAdvice responsible
 * for handling exceptions thrown by controllers.
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Handles DataAccessResourceFailureException and returns a ResponseEntity with HTTP status 404 (Not Found)
     * and the exception message as the response body.
     *
     * @param exception The DataAccessResourceFailureException to handle.
     * @param request   The current web request.
     * @return A ResponseEntity with HTTP status 404 and the exception message as the response body.
     */
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<String> handleDataAccessResourceFailureException(DataAccessResourceFailureException exception,
                                                                           WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
