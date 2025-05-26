package com.lubaszka.salarycalculator.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryCalculationListRequest {
    private List<SalaryCalculationRequest> salaryCalculationRequestList = new ArrayList<>();
}
