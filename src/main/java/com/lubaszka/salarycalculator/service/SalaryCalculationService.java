package com.lubaszka.salarycalculator.service;

import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationListRequest;
import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;

public interface SalaryCalculationService {
    double calculateSalary(SalaryCalculationRequest salaryCalculationRequest);

    double calculateSalaryByList(SalaryCalculationListRequest salaryCalculationRequestList);
}
