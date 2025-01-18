package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.api.HolidayParser;
import com.lubaszka.salarycalculator.api.PublicHolidayClient;
import com.lubaszka.salarycalculator.exception.DataProcessingException;
import com.lubaszka.salarycalculator.exception.ValidationException;
import com.lubaszka.salarycalculator.mapper.HolidayMapper;
import com.lubaszka.salarycalculator.model.Holiday;
import com.lubaszka.salarycalculator.model.dto.response.HolidayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HolidayServiceImplTest {

    @InjectMocks
    private HolidayServiceImpl holidayServiceImpl;

    @Mock
    private HolidayParser holidayParser;

    @Mock
    private HolidayMapper holidayMapper;

    @Mock
    private PublicHolidayClient publicHolidayClient;

    @Test
    void getAllHolidaysByYear_WithRightData_ShouldReturnHolidayResponseList() {
        HolidayResponse testHolidayResponse = new HolidayResponse(1, "New Year's Day", "2025-01-01", "1");
        Holiday holiday = new Holiday("2025-01-01", "Neujahr", "New Year's Day",
                "AT", true, true, "null", new ArrayList<>(), 1967);

        List<Holiday> holidayList = new ArrayList<>();
        holidayList.add(holiday);
        List<HolidayResponse> expectedHolidayResponseList = new ArrayList<>();
        expectedHolidayResponseList.add(testHolidayResponse);

        when(publicHolidayClient.getPublicHolidays(2025)).thenReturn(anyString());
        when(holidayParser.parseHolidays("someJsonText")).thenReturn(holidayList);
        when(holidayMapper.mapToHolidayResponse(any())).thenReturn(testHolidayResponse);

        assertEquals(expectedHolidayResponseList, holidayServiceImpl.getAllHolidaysByYear(2025));
    }

    @Test
    void getAllHolidaysByDateRange_WithRightData_ShouldReturnHolidayResponseList() {
        LocalDate startRange = LocalDate.of(2025, 1, 1);
        LocalDate endRange = LocalDate.of(2025, 1, 1);

        HolidayResponse testHolidayResponse = new HolidayResponse(1, "New Year's Day", "2025-01-01", "1");
        Holiday holiday = new Holiday("2025-01-01", "Neujahr", "New Year's Day",
                "AT", true, true, "null", new ArrayList<>(), 1967);

        List<Holiday> holidayList = new ArrayList<>();
        holidayList.add(holiday);
        List<HolidayResponse> expectedHolidayResponseList = new ArrayList<>();
        expectedHolidayResponseList.add(testHolidayResponse);

        when(publicHolidayClient.getPublicHolidays(2025)).thenReturn(anyString());
        when(holidayParser.parseHolidays("someJsonText")).thenReturn(holidayList);
        when(holidayMapper.mapToHolidayResponse(any())).thenReturn(testHolidayResponse);

        assertEquals(expectedHolidayResponseList, holidayServiceImpl.getAllHolidaysByDateRange(startRange, endRange));
    }

    @Test
    void getAllHolidaysByDateRange_WhenEndDateRangeBeforeStartDateRange_ThrowValidationException() {
        String expectedErrorMessage = "End date cannot be before start date!";

        LocalDate startRange = LocalDate.of(2025, 1, 1);
        LocalDate endRange = LocalDate.of(2025, 11, 29);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> holidayServiceImpl.getAllHolidaysByDateRange(endRange, startRange));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void getAllHolidaysByDateRange_WhenDateRangeInDifferentYears_ThrowDataProcessingException() {
        String expectedErrorMessage = "Start and end dates must be within the same year.";

        LocalDate startRange = LocalDate.of(2025, 1, 1);
        LocalDate endRange = LocalDate.of(2026, 11, 29);

        DataProcessingException exception = assertThrows(DataProcessingException.class,
                () -> holidayServiceImpl.getAllHolidaysByDateRange(startRange, endRange));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void getHolidayDatesInRange_WhenRightData_ShouldReturnLocalDateList() {
        List<LocalDate> expectedLocalDateList = List.of(LocalDate.of(2025, 1, 1));

        LocalDate startRange = LocalDate.of(2025, 1, 1);
        LocalDate endRange = LocalDate.of(2025, 1, 1);

        HolidayResponse testHolidayResponse = new HolidayResponse(1, "New Year's Day", "2025-01-01", "1");
        Holiday holiday = new Holiday("2025-01-01", "Neujahr", "New Year's Day",
                "AT", true, true, "null", new ArrayList<>(), 1967);

        List<Holiday> holidayList = new ArrayList<>();
        holidayList.add(holiday);

        when(publicHolidayClient.getPublicHolidays(2025)).thenReturn(anyString());
        when(holidayParser.parseHolidays("someJsonText")).thenReturn(holidayList);
        when(holidayMapper.mapToHolidayResponse(any())).thenReturn(testHolidayResponse);

        assertEquals(expectedLocalDateList , holidayServiceImpl.getHolidayDatesInRange(startRange, endRange));
    }
}