package com.springpoo2023;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Springpoo2023Application {

	public static void main(String[] args) {
		SpringApplication.run(Springpoo2023Application.class, args);
	}

}
