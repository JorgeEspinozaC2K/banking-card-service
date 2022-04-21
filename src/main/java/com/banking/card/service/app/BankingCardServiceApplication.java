package com.banking.card.service.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class BankingCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingCardServiceApplication.class, args);
	}

}
