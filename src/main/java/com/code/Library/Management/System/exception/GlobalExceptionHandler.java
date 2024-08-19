package com.code.Library.Management.System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ValidationError[] fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        String.valueOf(fieldError.getRejectedValue())))
                .toArray(ValidationError[]::new);
        var response = new ValidationErrorResponse(HttpStatus.BAD_REQUEST.name(),"Invalid Input", fieldErrors);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
