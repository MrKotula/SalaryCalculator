package com.lubaszka.salarycalculator.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolidayResponse {

    private Integer id;
    private String name;
    private String date;
    private String dayOfWeek;
}
