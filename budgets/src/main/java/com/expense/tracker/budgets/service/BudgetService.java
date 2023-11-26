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
		BudgetEntity checkBud = new BudgetEntity();
		checkBud = getBudget(bud.getCategory());
		if (checkBud.getId() > 0) {
			return "budget for the category already saved";
		}
		
		bud.setRemaining(bud.getLimits()-bud.getSpent());

		budRepo.save(bud);

		return "saved";
	}

	public BudgetEntity getBudget(String bud) {

		BudgetEntity response = new BudgetEntity();
		response.setId(0);
		try {
			response = budRepo.findByCategory(bud);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (response == null) {
			BudgetEntity response2 = new BudgetEntity();
			response2.setMonth(null);
			response2.setCreatedDate(null);
			response2.setId(0);

			return response2;
		}

		return response;
	}

	public String deleteBudget(String category) {
		try {
			budRepo.deleteByCategory(category);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "deleted";
	}

}
