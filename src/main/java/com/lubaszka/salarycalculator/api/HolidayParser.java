package com.lubaszka.salarycalculator.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lubaszka.salarycalculator.exception.DataProcessingException;
import com.lubaszka.salarycalculator.model.Holiday;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class HolidayParser {

    public List<Holiday> parseHolidays(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, new TypeReference<List<Holiday>>() {});
        } catch (JsonProcessingException e) {
            throw new DataProcessingException("Error parsing holidays JSON: " + e.getMessage());
        }
    }
}
