package com.lubaszka.salarycalculator.util;

import com.lubaszka.salarycalculator.exception.DataProcessingException;
import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import com.lubaszka.salarycalculator.service.NightShiftRateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaryCalculationUtilTest {

    private final LocalTime NIGHT_SHIFT_RATE_START_TIME = LocalTime.of(21, 0);

    @InjectMocks
    private SalaryCalculationUtil salaryCalculationUtil;

    @Mock
    private NightShiftRateService nightShiftRateService;

    @Test
    void getNettoNightShiftRate_WithRightData_ShouldReturnCountedValue() {
        double expectedValue = 3.89;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 12, 18, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 13, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        NightShiftRateResponse nightShiftRateResponse = new NightShiftRateResponse(
                1, Month.JANUARY, 2025, 5.55);

        when(nightShiftRateService.getNightShiftRateByMonthAndYear(Month.JANUARY, 2025))
                .thenReturn(nightShiftRateResponse);

        assertEquals(expectedValue, salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest));
    }

    @Test
    void getNettoNightShiftRate_WhenNightShiftResponseIsNull_ThrowsDataProcessingException() {
        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 12, 18, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 13, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        String expectedMessage = "Night shift rate not found for month: " + startWorkTime.getMonth();

        when(nightShiftRateService.getNightShiftRateByMonthAndYear(Month.JANUARY, 2025))
                .thenReturn(null);

        DataProcessingException exception = assertThrows(DataProcessingException.class,
                () -> salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void isNightShift_WhenTimeEqualsNightStartTime_ShouldReturnTrue() {
        LocalTime time = NIGHT_SHIFT_RATE_START_TIME;
        assertTrue(salaryCalculationUtil.isNightShift(time));
    }

    @Test
    void isNightShift_WhenTimeIsAfterNightStartTimeAndBeforeMidnight_ShouldReturnTrue() {
        LocalTime time = LocalTime.of(22, 0);
        assertTrue(salaryCalculationUtil.isNightShift(time));
    }

    @Test
    void isNightShift_WhenTimeIsAfterMidnightAndBeforeNightEndTime_ShouldReturnTrue() {
        LocalTime time = LocalTime.of(4, 30);
        assertTrue(salaryCalculationUtil.isNightShift(time));
    }

    @Test
    void isNightShift_WhenTimeIsBeforeNightStartTimeAndNotNightShift_ShouldReturnFalse() {
        LocalTime time = LocalTime.of(20, 0);
        assertFalse(salaryCalculationUtil.isNightShift(time));
    }

    @Test
    void isNightShift_WhenTimeIsAfterNightEndTimeAndNotNightShift_ShouldReturnFalse() {
        LocalTime time = LocalTime.of(6, 0);
        assertFalse(salaryCalculationUtil.isNightShift(time));
    }
}