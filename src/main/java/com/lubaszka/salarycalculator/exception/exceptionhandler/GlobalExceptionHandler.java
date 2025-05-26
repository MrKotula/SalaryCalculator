package com.lubaszka.salarycalculator.exception.exceptionhandler;

import com.lubaszka.salarycalculator.exception.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<ExceptionResponse> handleHttpException(
            HttpException ex, WebRequest request
    ) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getStatus(), ex.getMessage(),
                        request.getDescription(false).replace("uri=", "")),
                ex.getStatus()
        );
    }
}
