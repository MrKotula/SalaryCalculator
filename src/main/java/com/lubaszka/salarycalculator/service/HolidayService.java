package com.lubaszka.salarycalculator.service;

import com.lubaszka.salarycalculator.model.dto.response.HolidayResponse;
import java.time.LocalDate;
import java.util.List;

public interface HolidayService {
    List<HolidayResponse> getAllHolidaysByYear(int year);

    List<HolidayResponse> getAllHolidaysByDateRange(LocalDate startDate, LocalDate endDate);

    List<LocalDate> getHolidayDatesInRange(LocalDate startDate, LocalDate endDate);
}
