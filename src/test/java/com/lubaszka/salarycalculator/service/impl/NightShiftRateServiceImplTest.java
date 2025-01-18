package com.lubaszka.salarycalculator.service.impl;

import com.lubaszka.salarycalculator.mapper.NightShiftRateMapper;
import com.lubaszka.salarycalculator.model.NightShiftRate;
import com.lubaszka.salarycalculator.model.dto.response.NightShiftRateResponse;
import com.lubaszka.salarycalculator.repository.NightShiftRateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NightShiftRateServiceImplTest {

    @InjectMocks
    private NightShiftRateServiceImpl nightShiftRateService;

    @Mock
    private NightShiftRateRepository nightShiftRateRepository;

    @Mock
    private NightShiftRateMapper nightShiftRateMapper;

    @Test
    void getNightShiftRateByMonthAndYear_WithRightRequest_ShouldReturnNightShiftRateResponse() {
        NightShiftRate testNightShiftRate = new NightShiftRate(1, Month.AUGUST, 2025, 5.83);
        NightShiftRateResponse expectedNightShiftResponse = new NightShiftRateResponse(
                1, Month.AUGUST, 2025, 5.83);

        when(nightShiftRateRepository.findNightShiftRateByMonthAndYear(Month.AUGUST, 2025))
                .thenReturn(testNightShiftRate);
        when(nightShiftRateMapper.mapToNightShiftRateResponse(testNightShiftRate)).thenReturn(expectedNightShiftResponse);

        assertEquals(expectedNightShiftResponse, nightShiftRateService.getNightShiftRateByMonthAndYear(
                Month.AUGUST, 2025));
    }
}