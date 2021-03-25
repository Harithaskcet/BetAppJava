package com.example.BetApp;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@CrossOrigin(origins = "http://localhost:3000")
@SpringBootApplication
public class BetAppApplication {
	@Bean
	CorsConfigurationSource corsConfigurationSource() {    
	CorsConfiguration configuration = new CorsConfiguration();    
	configuration.setAllowedOrigins(Arrays.asList("*"));    
	configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));    
	configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));    
	configuration.setAllowCredentials(true);    
	configuration.addAllowedOrigin("http://localhost:3000");
	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();    
	source.registerCorsConfiguration("/**", configuration);    
	return source;}
	public static void main(String[] args) {
		SpringApplication.run(BetAppApplication.class, args);
	}

}
