package com.jeffersonssousa.investHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InvestHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestHubApplication.class, args);
	}

}
