package com.code.Library.Management.System.exception;

import com.code.Library.Management.System.exception.v2.ErrorResponse;
public class ValidationErrorResponse extends ErrorResponse {
    private final ValidationError[] fieldErrors;

    public ValidationErrorResponse(String code, String message, ValidationError[] fieldErrors) {
        super(code, message);
        this.fieldErrors = fieldErrors;
    }

    public ValidationError[] getFieldErrors() {
        return fieldErrors;
    }
}