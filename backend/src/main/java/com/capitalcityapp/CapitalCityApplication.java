package com.capitalcityapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//lsof -i :8080 					list processes on port 8080
//kill <PID> 						kill process ID
//brew services start postgresql 	start postgres service
//psql -d postgres  				access postgres databases
//http://localhost:8080/countries	endpoint for all countries

@SpringBootApplication
public class CapitalCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapitalCityApplication.class, args);
	}

}
