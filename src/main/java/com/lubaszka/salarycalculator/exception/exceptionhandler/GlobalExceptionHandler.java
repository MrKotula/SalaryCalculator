package com.lubaszka.salarycalculator.exception.exceptionhandler;

import com.lubaszka.salarycalculator.exception.HttpException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpException.class)
    public String handleHttpException(HttpException ex, WebRequest request, Model model) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                ex.getStatus(), ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        model.addAttribute("timestamp", exceptionResponse.getTimestamp());
        model.addAttribute("status", exceptionResponse.getStatus());
        model.addAttribute("message", exceptionResponse.getMessage());
        model.addAttribute("url", exceptionResponse.getUrl());

        return "error_page";
    }
}
