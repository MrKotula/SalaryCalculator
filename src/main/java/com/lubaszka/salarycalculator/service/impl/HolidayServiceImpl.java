package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.api.HolidayParser;
import com.lubaszka.salarycalculator.api.PublicHolidayClient;
import com.lubaszka.salarycalculator.exception.DataProcessingException;
import com.lubaszka.salarycalculator.exception.ValidationException;
import com.lubaszka.salarycalculator.mapper.HolidayMapper;
import com.lubaszka.salarycalculator.model.Holiday;
import com.lubaszka.salarycalculator.model.dto.response.HolidayResponse;
import com.lubaszka.salarycalculator.service.HolidayService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HolidayServiceImpl implements HolidayService {

    private static final String DATE_RANGE_YEAR_EXCEPTION = "Start and end dates must be within the same year.";
    private static final String START_RANGE_EXCEPTION = "End date cannot be before start date!";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final PublicHolidayClient publicHolidayClient;
    private final HolidayParser holidayParser;
    private final HolidayMapper holidayMapper;

    @Override
    public List<HolidayResponse> getAllHolidaysByYear(int year) {
        String jsonHolidays = publicHolidayClient.getPublicHolidays(year);
        List<Holiday> holidays = holidayParser.parseHolidays(jsonHolidays);

        return holidays.stream()
                .map(holidayMapper::mapToHolidayResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<HolidayResponse> getAllHolidaysByDateRange(LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);

        if (startDate.getYear() != endDate.getYear()) {
            throw new DataProcessingException(DATE_RANGE_YEAR_EXCEPTION);
        }

        int year = startDate.getYear();
        List<HolidayResponse> holidays = getAllHolidaysByYear(year);

        return holidays.stream()
                .filter(holiday -> {
                    LocalDate holidayDate = convertStringToLocalDate(holiday.getDate());
                    return holidayDate.getYear() == year;
                })
                .filter(holiday -> {
                    LocalDate holidayDate = convertStringToLocalDate(holiday.getDate());
                    return !holidayDate.isBefore(startDate) &&
                            !holidayDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<LocalDate> getHolidayDatesInRange(LocalDate startDate, LocalDate endDate) {
        List<HolidayResponse> holidays = getAllHolidaysByDateRange(startDate, endDate);

        return holidays.stream()
                .map(holidayResponse -> LocalDate.parse(holidayResponse.getDate()))
                .collect(Collectors.toList());
    }

    private LocalDate convertStringToLocalDate(String dateString) {
        return LocalDate.parse(dateString, DATE_FORMATTER);
    }

    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new ValidationException(START_RANGE_EXCEPTION);
        }
    }
}
