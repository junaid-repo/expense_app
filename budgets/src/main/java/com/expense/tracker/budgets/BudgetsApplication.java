package com.expense.tracker.budgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BudgetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetsApplication.class, args);
	}

}
