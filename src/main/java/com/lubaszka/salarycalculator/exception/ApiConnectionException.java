package com.lubaszka.salarycalculator.exception;

import org.springframework.http.HttpStatus;

public class ApiConnectionException extends HttpException {

    public ApiConnectionException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }
}
