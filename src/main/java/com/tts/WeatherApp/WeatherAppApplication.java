package com.tts.WeatherApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tts.WeatherApp.domain.ZipCode;
import com.tts.WeatherApp.repository.ZipCodeRepository;

@SpringBootApplication
public class WeatherAppApplication {
	private static final Logger log = LoggerFactory.getLogger(WeatherAppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}



	@Bean
	public CommandLineRunner demo(ZipCodeRepository repository) {
		return (args) -> {

			log.info("Zipcodes found with findAll():");
			log.info("-------------------------------");
			for (ZipCode zipCode : repository.findAll()) {
				log.info(zipCode.toString());
			}
			log.info("");

			// read an individual zip code by ID
			repository.findById(1L)
					.ifPresent(zipCode -> {
						log.info("Zip code found with findById(1L):");
						log.info("--------------------------------");
						log.info(zipCode.toString());
						log.info("");
					});

			log.info("");
		};
	}
}
