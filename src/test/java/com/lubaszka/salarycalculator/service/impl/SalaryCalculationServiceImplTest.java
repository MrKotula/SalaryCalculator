package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import com.lubaszka.salarycalculator.service.HolidayService;
import com.lubaszka.salarycalculator.util.SalaryCalculationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalaryCalculationServiceImplTest {

    @InjectMocks
    private SalaryCalculationServiceImpl salaryCalculationService;

    @Mock
    private SalaryCalculationUtil salaryCalculationUtil;

    @Mock
    private HolidayService holidayService;

    @Test
    void calculateSalary_WithRightDataForNightShiftInHoliday_ShouldReturnCountedValue() {
        double expectedValue = 345.01;

        double nettoNightShiftValue = 3.89;
        List<LocalDate> localDateList = List.of(LocalDate.of(2025, 1, 1));

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 2, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForMixedShiftInHoliday_ShouldReturnCountedValue() {
        double expectedValue = 589.08;

        double nettoNightShiftValue = 3.89;
        List<LocalDate> localDateList = List.of(LocalDate.of(2025, 1, 1));

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 1, 18, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 2, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForNightShiftInSunday_ShouldReturnCountedValue() {
        double expectedValue = 345.01;

        double nettoNightShiftValue = 3.89;
        List<LocalDate> localDateList = List.of();

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 12, 22, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 13, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForMixedShiftInSunday_ShouldReturnCountedValue() {
        double expectedValue = 403.08;

        double nettoNightShiftValue = 3.89;
        List<LocalDate> localDateList = List.of();

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 12, 18, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 13, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForMixedShiftInSimpleDay_ShouldReturnCountedValue() {
        double expectedValue = 403.08;

        double nettoNightShiftValue = 3.89;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 12, 18, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 13, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForDayShift_ShouldReturnCountedValue() {
        double expectedValue = 248.0;

        List<LocalDate> localDateList = List.of();

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 2, 6, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 2, 14, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithRightDataForDayShiftInHoliday_ShouldReturnCountedValue() {
        double expectedValue = 496.0;

        List<LocalDate> localDateList = List.of();

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 1, 6, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 1, 14, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(holidayService.getHolidayDatesInRange(startWorkTime.toLocalDate(), endWorkTime.toLocalDate()))
                .thenReturn(localDateList);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenReturn(expectedValue);

        assertEquals(expectedValue, salaryCalculationService.calculateSalary(salaryCalculationRequest));
    }

    @Test
    void calculateSalary_WithHolidayAndNightShift_ShouldReturnAdjustedTotalSalary() {
        double expectedSalary = 357.62;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 2, 6, 15);
        double baseRate = 31.0;
        double nettoNightShiftValue = 3.89;

        List<LocalDate> holidayDays = List.of(LocalDate.of(2025, 1, 1));

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        when(holidayService.getHolidayDatesInRange(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(holidayDays);
        when(salaryCalculationUtil.isNightShift(any(LocalTime.class))).thenReturn(true);
        when(salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest)).thenReturn(nettoNightShiftValue);
        when(salaryCalculationUtil.roundToTwoDecimalPlaces(anyDouble())).thenAnswer(invocation -> {
            double value = invocation.getArgument(0);
            return Math.round(value * 100.0) / 100.0;
        });

        double actualSalary = salaryCalculationService.calculateSalary(salaryCalculationRequest);

        assertEquals(expectedSalary, actualSalary);
    }
}