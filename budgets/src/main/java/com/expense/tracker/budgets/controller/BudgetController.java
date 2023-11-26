package com.expense.tracker.budgets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.budgets.entity.BudgetEntity;
import com.expense.tracker.budgets.service.BudgetService;

@RestController
@RequestMapping("/expense/budgets")
public class BudgetController {

	@Autowired
	BudgetService serv;

	@PostMapping("/set")
	ResponseEntity<String> setBudget(@RequestBody BudgetEntity bud) {

		String response = serv.setBudget(bud);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get/{category}")
	ResponseEntity<BudgetEntity> getBudget(@PathVariable String category) {

		BudgetEntity response = new BudgetEntity();
		response = serv.getBudget(category);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@DeleteMapping("/delete/{category}")
	ResponseEntity<String> removeBudget(@PathVariable String category) {
		String response = serv.deleteBudget(category);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
