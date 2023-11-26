package com.expense.tracker.budgets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.budgets.entity.BudgetEntity;
import com.expense.tracker.budgets.service.BudgetService;

@RestController
@RequestMapping("/expense/budget")
public class BudgetController {
	
	@Autowired
	BudgetService serv;
	
	@PostMapping("/set")
	ResponseEntity<String> setBudget(@RequestBody BudgetEntity bud){
		
		String response=serv.setBudget(bud);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
		
		
		
	}

}
