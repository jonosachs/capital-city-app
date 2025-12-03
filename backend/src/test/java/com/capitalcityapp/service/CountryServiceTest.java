package com.capitalcityapp.service;

import com.capitalcityapp.domain.Country;
import com.capitalcityapp.dto.CountryResponse;
import com.capitalcityapp.repository.CountryRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryServiceTest {
    @Mock CountryRepo repo;
    @InjectMocks CountryService service;

    @Test
    void allReturnsAsExpected() {
        List<Country> countries = List.of(new Country("Australia", "Canberra"), new Country("Argentina", "Buenos Aires"));
        when(repo.findAll()).thenReturn(countries);

        List<CountryResponse> result = service.findAll();
        assertEquals("Australia", result.get(0).country());
        assertEquals("Argentina", result.get(1).country());
    }

    @Test
    void getCountryReturnsAsExpected() {
        Country country = new Country("Australia", "Canberra");
        when(repo.findByCountryIgnoreCase("Australia")).thenReturn(Optional.of(country));

        CountryResponse response = service.findByName("Australia");
        assertEquals("Australia", response.country());
    }
}