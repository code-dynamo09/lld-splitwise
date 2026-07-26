package com.algocamp.lld_splitwise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LldSplitwiseApplication {

	public static void main(String[] args) {
		SpringApplication.run(LldSplitwiseApplication.class, args);
	}

}
