package com.lubaszka.salarycalculator.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryCalculationRequest {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private double baseRate;
}
