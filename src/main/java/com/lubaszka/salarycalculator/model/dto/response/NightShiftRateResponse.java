package com.lubaszka.salarycalculator.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Month;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NightShiftRateResponse {

    private Integer id;
    private Month month;
    private Integer year;
    private double bruttoRate;
}
