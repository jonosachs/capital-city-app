package com.capitalcityapp.service;

import com.capitalcityapp.domain.Country;
import com.capitalcityapp.dto.CountryResponse;
import com.capitalcityapp.mapper.CountryMapper;
import com.capitalcityapp.repository.CountryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {

    private final CountryRepo repo;

    public CountryService(CountryRepo repo) {
        this.repo = repo;
    }

    public List<CountryResponse> findAll() {
        var all = repo.findAll().stream()
                .map(CountryMapper::toDto)
                .toList();

        if (all.isEmpty())
            throw new EntityNotFoundException("No countries found");

        return all;
    }

    public CountryResponse findByName(String name) {
        Country country = repo.findByCountryIgnoreCase(name)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));

        var dto = CountryMapper.toDto(country);

        if (dto.country() == null || dto.capital() == null)
            throw new EntityNotFoundException("Country or Capital not found");

        return CountryMapper.toDto(country);
    }
}
