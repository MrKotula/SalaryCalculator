package com.lubaszka.salarycalculator.service;

import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import java.time.Month;

public interface NightShiftRateService {
    NightShiftRateResponse getNightShiftRateByMonthAndYear(Month month, int year);
}
