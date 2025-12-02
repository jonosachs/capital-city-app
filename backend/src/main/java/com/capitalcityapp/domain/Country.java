package com.capitalcityapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String capital;

    public Country() {}

    public Country(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public Long getId() {
        return id;
    }
}
