package com.expense.tracker.budgets.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.budgets.entity.BudgetEntity;
import com.expense.tracker.budgets.repository.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	BudgetRepository budRepo;

	public String setBudget(BudgetEntity bud) {
		budRepo.save(bud);

		return "saved";
	}

}
