package com.capitalcityapp.dto;

import jakarta.validation.constraints.NotBlank;

public record CountryRequest (
        @NotBlank String country
) {}
