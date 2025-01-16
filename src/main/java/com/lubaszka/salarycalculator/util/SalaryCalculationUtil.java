package com.lubaszka.salarycalculator.util;

import com.lubaszka.salarycalculator.exception.DataProcessingException;
import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import com.lubaszka.salarycalculator.service.NightShiftRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.Month;

import static java.lang.Math.round;

@Component
@AllArgsConstructor
public class SalaryCalculationUtil {

    private static final LocalTime NIGHT_START_TIME = LocalTime.of(21, 0);
    private static final LocalTime NIGHT_END_TIME = LocalTime.of(5, 0);
    private static final double NIGHT_SHIFT_DEDUCTION_PERCENT = 0.3;
    private static final double ROUNDING_FACTOR = 100.0;

    private static final String NIGHT_SHIFT_RATE_NOT_FOUND_EXCEPTION = "Night shift rate not found for month: ";

    private final NightShiftRateService nightShiftRateService;

    public double getNettoNightShiftRate(SalaryCalculationRequest salaryCalculationRequest) {
        Month month = salaryCalculationRequest.getStartDateTime().getMonth();
        int year = salaryCalculationRequest.getStartDateTime().getYear();

        NightShiftRateResponse nightShiftRateResponse = nightShiftRateService.getNightShiftRateByMonthAndYear(month, year);

        if (nightShiftRateResponse == null) {
            throw new DataProcessingException(NIGHT_SHIFT_RATE_NOT_FOUND_EXCEPTION + month);
        }

        double bruttoNightShiftRate = nightShiftRateResponse.getBruttoRate();

        double deduction = bruttoNightShiftRate * NIGHT_SHIFT_DEDUCTION_PERCENT;
        double nettoNightShiftRate = bruttoNightShiftRate - deduction;

        return roundToTwoDecimalPlaces(nettoNightShiftRate);
    }

    public boolean isNightShift(LocalTime time) {
        return (time == NIGHT_START_TIME || time.isAfter(NIGHT_START_TIME) || time.isBefore(NIGHT_END_TIME));
    }

    public double roundToTwoDecimalPlaces(double value) {
        return round(value * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }
}
