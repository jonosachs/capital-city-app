package com.capitalcityapp.repository;

import com.capitalcityapp.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepo extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryIgnoreCase(String country);
}
