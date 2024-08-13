package com.code.Library.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(Exception ex){
        return buildResponse(new ErrorResponse(HttpStatus.NOT_FOUND,ex.getMessage(),
                HttpStatus.NOT_FOUND.value()));
    }

    private ResponseEntity<ErrorResponse> buildResponse(ErrorResponse errorResponse){
        return new ResponseEntity<>(errorResponse,errorResponse.getStatus());
    }
}
