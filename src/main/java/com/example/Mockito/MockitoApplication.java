package com.example.Mockito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MockitoApplication {

	public static void main(String[] args) {

		SpringApplication.run(MockitoApplication.class, args);
	}

}
