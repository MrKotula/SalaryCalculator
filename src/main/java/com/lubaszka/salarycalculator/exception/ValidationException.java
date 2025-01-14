package com.lubaszka.salarycalculator.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends HttpException {

    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
