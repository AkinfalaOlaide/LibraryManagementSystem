package com.code.Library.Management.System.exception;

public record ValidationError(String field, String message, String rejectedValue) {
}