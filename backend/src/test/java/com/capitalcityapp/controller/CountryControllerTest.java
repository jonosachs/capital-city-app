package com.capitalcityapp.controller;

import com.capitalcityapp.domain.Country;
import com.capitalcityapp.mapper.CountryMapper;
import com.capitalcityapp.service.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {
    @Autowired MockMvc mockMvc;
    @MockBean CountryService service;

    @Test
    void getCountryByName() throws Exception {
        Country country = new Country("Australia", "Canberra");
        when(service.findByName("Australia"))
                .thenReturn(CountryMapper.toDto(country));

        mockMvc.perform(get("/countries/Australia"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("country").value("Australia"))
                .andExpect(jsonPath("capital").value("Canberra"));
    }

}