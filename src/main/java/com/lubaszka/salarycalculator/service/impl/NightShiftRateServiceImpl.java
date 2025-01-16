package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.mapper.NightShiftRateMapper;
import com.lubaszka.salarycalculator.model.NightShiftRate;
import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import com.lubaszka.salarycalculator.repository.NightShiftRateRepository;
import com.lubaszka.salarycalculator.service.NightShiftRateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Month;

@Service
@AllArgsConstructor
public class NightShiftRateServiceImpl implements NightShiftRateService {

    private final NightShiftRateRepository nightShiftRateRepository;
    private final NightShiftRateMapper nightShiftRateMapper;

    @Override
    public NightShiftRateResponse getNightShiftRateByMonthAndYear(Month month, int year) {
        NightShiftRate nightShiftRate = nightShiftRateRepository.findNightShiftRateByMonthAndYear(month, year);

        return nightShiftRateMapper.mapToNightShiftRateResponse(nightShiftRate);
    }
}
