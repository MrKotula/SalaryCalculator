package com.lubaszka.salarycalculator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public HttpException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
