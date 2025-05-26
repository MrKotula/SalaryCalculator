package com.lubaszka.salarycalculator.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryCalculationSubRequest {

    private LocalDate currentDay;
    private List<LocalDate> holidayDays;
    private LocalTime currentHour;
    private double currentRate;
    private double nightShiftRate;
}
