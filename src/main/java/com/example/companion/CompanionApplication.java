package com.example.companion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class CompanionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanionApplication.class, args);
	}

}
