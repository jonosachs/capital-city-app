package com.example.capitalcityapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500") // front-end's origin
@RestController
class CountryController {

    private final CountryRepo countryRepo;

    CountryController(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @GetMapping("/countries")
    List<Country> all() {
        return countryRepo.findAll();
    }

    @GetMapping(value = "/countries/{countryName}", produces = "application/json")
    Country getCountry(@PathVariable String countryName) {
        return countryRepo.findAll().stream()
                .filter(country -> country.getCountry().equalsIgnoreCase(countryName))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Country \"" + countryName + "\" not found"));
    }



}
