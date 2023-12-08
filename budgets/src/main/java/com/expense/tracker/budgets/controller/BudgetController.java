package com.expense.tracker.budgets.controller;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.budgets.dto.UpdateBudgetDTO;
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

	@GetMapping("/get/{category}/{month}")
	ResponseEntity<BudgetEntity> getBudget(@PathVariable String category, @PathVariable Month month) {

		BudgetEntity response = new BudgetEntity();
		response = serv.getBudget(category, month);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@DeleteMapping("/delete/{category}")
	ResponseEntity<String> removeBudget(@PathVariable String category) {
		String response = serv.deleteBudget(category);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/all")
	ResponseEntity<List<BudgetEntity>> getBudgetList() {
		List<BudgetEntity> response = new ArrayList<>();
		response = serv.getBudgetList();

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@PutMapping("/update")
	ResponseEntity<String> updateBudget(@RequestBody UpdateBudgetDTO request) {
		String response = serv.updateBudgetSpent(request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
