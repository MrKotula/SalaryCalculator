package com.lubaszka.salarycalculator.controller;

import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationListRequest;
import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import com.lubaszka.salarycalculator.service.SalaryCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class SalaryCalculationController {

    private static final String BASE_URL = "/api/lubaszka";
    private static final String CALCULATE = "/salary/calculate";
    private static final String CALCULATE_LIST = "/salary/calculate_list";

    private final SalaryCalculationService salaryCalculationService;

    @GetMapping(BASE_URL + CALCULATE)
    public String countSalary() {
        return "salaryCalculationForm";
    }

    @PostMapping(BASE_URL + CALCULATE)
    public String countSalary(@ModelAttribute SalaryCalculationRequest salaryCalculationRequest, Model model) {
        model.addAttribute("salaryResult", salaryCalculationService.calculateSalary(salaryCalculationRequest));

        return "salaryCalculationForm";
    }

    @GetMapping(BASE_URL + CALCULATE_LIST)
    public String calculateSalaries(Model model) {
        model.addAttribute("requests", new SalaryCalculationListRequest());
        return "salaryCalculationListForm";
    }

    @PostMapping(BASE_URL + CALCULATE_LIST)
    public String calculateSalaries(@ModelAttribute SalaryCalculationListRequest requests, Model model) {
        System.out.println("Received requests: " + requests.getSalaryCalculationRequestList());
        model.addAttribute("result", salaryCalculationService.calculateSalaryByList(requests));

        return "salaryCalculationListForm";
    }
}
