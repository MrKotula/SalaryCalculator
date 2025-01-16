package com.lubaszka.salarycalculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Entity
@Table(name = "night_shift_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NightShiftRate {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "month")
    @Enumerated(EnumType.STRING)
    private Month month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "brutto_rate")
    private double bruttoRate;
}
