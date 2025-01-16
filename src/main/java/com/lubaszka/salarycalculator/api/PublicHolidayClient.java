package com.lubaszka.salarycalculator.api;

import com.lubaszka.salarycalculator.exception.ApiConnectionException;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PublicHolidayClient {

    private static final String BASE_URL = "https://date.nager.at/api/v3/PublicHolidays/";
    private static final String COUNTRY_CODE = "PL";
    private static final int HTTP_STATUS_OK = 200;

    public String getPublicHolidays(int year) {
        String url = BASE_URL + year + "/" + COUNTRY_CODE;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == HTTP_STATUS_OK) {
                return response.body();
            } else {
                throw new ApiConnectionException("Cannot get data. Status: " + response.statusCode());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new ApiConnectionException("Request was interrupted.");
        } catch (IOException e) {
            throw new ApiConnectionException("IO error occurred: " + e.getMessage());
        }
    }
}
