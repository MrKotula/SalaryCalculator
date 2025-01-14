package com.lubaszka.salarycalculator.exception;

import org.springframework.http.HttpStatus;

public class DataProcessingException extends HttpException {
    public DataProcessingException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
