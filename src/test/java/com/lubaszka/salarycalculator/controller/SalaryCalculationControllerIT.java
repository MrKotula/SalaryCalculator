package com.lubaszka.salarycalculator.controller;

import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationListRequest;
import com.lubaszka.salarycalculator.model.dto.request.SalaryCalculationRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class SalaryCalculationControllerIT {

    protected static MockMvc mockMvc;

    @Container
    public static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("salary_calculator_test")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    static void setUp(@Autowired DataSource dataSource) {
        System.setProperty("spring.datasource.url", postgresSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresSQLContainer.getPassword());

        try (Connection connection = dataSource.getConnection()) {
            callSqlQueryFromFile(connection, "create_table_night_shift_rate.sql");
            callSqlQueryFromFile(connection, "init_table night_shift_rate.sql");
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to database", e);
        }
    }

    @BeforeEach
    void setMockMvc(@Autowired WebApplicationContext context) {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    void countSalary_withRightLink_ShouldReturnSalaryCalculationForm() throws Exception {
        mockMvc.perform(get("/api/lubaszka/salary/calculate"))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationForm"));
    }

    @Test
    void countSalary_WithRightDataForHolidayWithNightShift_ShouldCalculateSalaryAndReturnFormWithResult() throws Exception {
        double expectedValue = 345.01;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 2, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        mockMvc.perform(post("/api/lubaszka/salary/calculate")
                        .flashAttr("salaryCalculationRequest", salaryCalculationRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationForm"))
                .andExpect(model().attribute("salaryResult", expectedValue));
    }

    @Test
    void countSalary_WithRightDataForWeekdayWithNightShift_ShouldCalculateSalaryAndReturnFormWithResult() throws Exception {
        double expectedValue = 275.23;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 15, 22, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 16, 6, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        mockMvc.perform(post("/api/lubaszka/salary/calculate")
                        .flashAttr("salaryCalculationRequest", salaryCalculationRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationForm"))
                .andExpect(model().attribute("salaryResult", expectedValue));
    }

    @Test
    void countSalary_WithRightDataForWeekdayWithDayShift_ShouldCalculateSalaryAndReturnFormWithResult() throws Exception {
        double expectedValue = 248.00;

        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 15, 6, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 15, 14, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        mockMvc.perform(post("/api/lubaszka/salary/calculate")
                        .flashAttr("salaryCalculationRequest", salaryCalculationRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationForm"))
                .andExpect(model().attribute("salaryResult", expectedValue));
    }

    @Test
    void calculateSalaries_withRightLink_ShouldReturnSalaryCalculationListForm() throws Exception {
        mockMvc.perform(get("/api/lubaszka/salary/calculate_list"))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationListForm"));
    }

    @Test
    void calculateSalaries_WithRightDataForMixedShifts_ShouldCalculateSalaryAndReturnFormWithResult() throws Exception {
        double expectedValue = 868.24;

        SalaryCalculationListRequest salaryCalculationListRequest = getSalaryCalculationListRequest();
        System.out.println("Salary Calculation List Request: " + salaryCalculationListRequest);

        mockMvc.perform(post("/api/lubaszka/salary/calculate_list")
                        .flashAttr("salaryCalculationListRequest", salaryCalculationListRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("salaryCalculationListForm"))
                .andExpect(model().attribute("result", expectedValue));
    }

    private static void callSqlQueryFromFile(Connection connection, String fileName) {
        ScriptUtils.executeSqlScript(
                connection, new ClassPathResource("db/" + fileName)
        );
    }

    private SalaryCalculationListRequest getSalaryCalculationListRequest() {
        LocalDateTime startWorkTime = LocalDateTime.of(2025, 1, 15, 6, 0);
        LocalDateTime endWorkTime = LocalDateTime.of(2025, 1, 15, 14, 0);
        double baseRate = 31.0;

        SalaryCalculationRequest salaryCalculationRequest = new SalaryCalculationRequest(
                startWorkTime, endWorkTime, baseRate);

        LocalDateTime secondStartWorkTime = LocalDateTime.of(2025, 1, 1, 22, 0);
        LocalDateTime secondEndWorkTime = LocalDateTime.of(2025, 1, 2, 6, 0);
        double secondBaseRate = 31.0;

        SalaryCalculationRequest secondSalaryCalculationRequest = new SalaryCalculationRequest(
                secondStartWorkTime, secondEndWorkTime, secondBaseRate);

        LocalDateTime thirdStartWorkTime = LocalDateTime.of(2025, 1, 15, 22, 0);
        LocalDateTime thirdEndWorkTime = LocalDateTime.of(2025, 1, 16, 6, 0);
        double thirdBaseRate = 31.0;

        SalaryCalculationRequest thirdSalaryCalculationRequest = new SalaryCalculationRequest(
                thirdStartWorkTime, thirdEndWorkTime, thirdBaseRate);

        List<SalaryCalculationRequest> salaryCalculationRequests = List.of(
                salaryCalculationRequest,
                secondSalaryCalculationRequest,
                thirdSalaryCalculationRequest
        );

        return new SalaryCalculationListRequest(salaryCalculationRequests);
    }
}