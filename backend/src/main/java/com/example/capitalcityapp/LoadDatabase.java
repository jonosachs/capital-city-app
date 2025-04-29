package com.example.capitalcityapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.InputStream;
import java.util.List;
import java.util.Map;


@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(CountryRepo countryRepo) {

        return args -> {

            countryRepo.deleteAll();

            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/country-by-capital-city.json");

            List<Map<String,String>> entries = mapper.readValue(inputStream, new TypeReference<>(){});

            for(Map<String,String> entry : entries) {
                String country = entry.get("country");
                String city = entry.get("city");
                countryRepo.save(new Country(country, city));
            }
            System.out.println(entries.size() + " countries added to database");
        };
    }



}
