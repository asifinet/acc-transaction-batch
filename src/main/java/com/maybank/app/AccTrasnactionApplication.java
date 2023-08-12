package com.maybank.app;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class AccTrasnactionApplication {
	
	// The main method that starts the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(AccTrasnactionApplication.class, args);
	}
}
