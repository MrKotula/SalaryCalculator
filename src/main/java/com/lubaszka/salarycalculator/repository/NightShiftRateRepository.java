package com.lubaszka.salarycalculator.repository;

import com.lubaszka.salarycalculator.model.NightShiftRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Month;

@Repository
public interface NightShiftRateRepository extends JpaRepository<NightShiftRate, Integer> {

    NightShiftRate findNightShiftRateByMonthAndYear(Month month, int year);
}
