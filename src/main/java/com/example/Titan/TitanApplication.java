package com.example.Titan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // Enable scheduled tasks
@EnableCaching
public class TitanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TitanApplication.class, args);

	}

}
