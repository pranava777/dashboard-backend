package com.dashboard.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//starting poing of spring boot application, @SpringBootApplication will make understand compiler that this is the
//main method

@SpringBootApplication
public class SecureAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureAppApplication.class, args);
	}

}

