package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import com.lubaszka.salarycalculator.service.HolidayService;
import com.lubaszka.salarycalculator.service.SalaryCalculationService;
import com.lubaszka.salarycalculator.util.SalaryCalculationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SalaryCalculationServiceImpl implements SalaryCalculationService {

    private static final byte ONE_HOUR = 1;
    private static final byte ZERO = 0;
    private static final double HOLIDAY_MULTIPLIER = 2.0;
    private static final double MINUTES_IN_HOUR = 60.0;

    private final HolidayService holidayService;
    private final SalaryCalculationUtil salaryCalculationUtil;

    @Override
    public double calculateSalary(SalaryCalculationRequest salaryCalculationRequest) {
        double totalSalary = 0.0;

        double baseRate = salaryCalculationRequest.getBaseRate();
        double nightShiftRate = salaryCalculationUtil.getNettoNightShiftRate(salaryCalculationRequest);

        LocalDateTime currentTime = salaryCalculationRequest.getStartDateTime();
        LocalDateTime endTime = salaryCalculationRequest.getEndDateTime();

        List<LocalDate> holidayDays = holidayService.getHolidayDatesInRange(currentTime.toLocalDate(), endTime.toLocalDate());

        while (currentTime.isBefore(endTime)) {
            LocalDate currentDay = currentTime.toLocalDate();
            LocalTime currentHour = currentTime.toLocalTime();

            LocalDateTime nextHour = currentTime.plusHours(ONE_HOUR).withMinute(ZERO).withSecond(ZERO).withNano(ZERO);
            LocalDateTime effectiveEnd = nextHour.isAfter(endTime) ? endTime : nextHour;
            long minutesWorked = Duration.between(currentTime, effectiveEnd).toMinutes();

            double currentRate = calculateHourlyRate(currentDay, holidayDays, currentHour, baseRate, nightShiftRate);

            double hourlySalary = currentRate * (minutesWorked / MINUTES_IN_HOUR);
            totalSalary += hourlySalary;

            currentTime = effectiveEnd;
        }

        return salaryCalculationUtil.roundToTwoDecimalPlaces(totalSalary);
    }

    private double calculateHourlyRate(LocalDate currentDay, List<LocalDate> holidayDays, LocalTime currentHour,
                                       double currentRate, double nightShiftRate) {
        boolean isNightShift = salaryCalculationUtil.isNightShift(currentHour);

        if (isHoliday(currentDay, holidayDays)) {
            if (isNightShift) {
                currentRate += nightShiftRate;
            }
            currentRate *= HOLIDAY_MULTIPLIER;
        } else if (isNightShift) {
            currentRate += nightShiftRate;
        }

        return currentRate;
    }

    private boolean isHoliday(LocalDate date, List<LocalDate> holidayDays) {
        return holidayDays.contains(date) || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
