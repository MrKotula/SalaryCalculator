package com.lubaszka.salarycalculator.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
class LocaleControllerTest {

    protected static MockMvc mockMvc;

    @BeforeEach
    public void setup(@Autowired WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testChangeLanguage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/changeLanguage")
                        .header("Referer", "/api/lubaszka/salary/calculate")
                        .param("lang", "ua"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/lubaszka/salary/calculate"));
    }
}