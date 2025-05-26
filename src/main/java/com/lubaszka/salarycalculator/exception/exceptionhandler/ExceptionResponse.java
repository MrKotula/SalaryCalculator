package com.lubaszka.salarycalculator.exception.exceptionhandler;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class ExceptionResponse {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String timestamp;
    private final int status;
    private String message;
    private String url;

    public ExceptionResponse(HttpStatusCode status, String message, String url) {
        this.timestamp = LocalDateTime.now().format(FORMATTER);
        this.status = status.value();
        this.url = url;
        this.message = message;
    }
}
