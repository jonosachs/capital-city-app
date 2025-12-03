package com.capitalcityapp.controller;

import com.capitalcityapp.dto.CountryResponse;
import com.capitalcityapp.service.CountryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500") // front-end's origin
@RequestMapping("/countries")
@RestController
class CountryController {

    private final CountryService countryService;

    CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("")
    List<CountryResponse> all() {
        return countryService.findAll();
    }

    @GetMapping("/{country}")
    CountryResponse getCountry(@PathVariable String country) {
        return countryService.findByName(country);
    }

}
