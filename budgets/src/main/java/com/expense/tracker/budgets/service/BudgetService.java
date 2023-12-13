package com.expense.tracker.budgets.service;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.budgets.api.BudgetFacade;
import com.expense.tracker.budgets.dto.UpdateBudgetDTO;
import com.expense.tracker.budgets.entity.BudgetEntity;
import com.expense.tracker.budgets.repository.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	BudgetRepository budRepo;

	@Autowired
	BudgetFacade bf;

	public String setBudget(BudgetEntity bud) {
		BudgetEntity checkBud = new BudgetEntity();

		boolean validateCategory = bf.getCategoryDetails(bud.getCategory());
		if (validateCategory == false) {
			return "invalid category";
		}

		checkBud = getBudget(bud.getCategory(), bud.getMonth());
		if (checkBud.getId() > 0) {
			return "budget for the category already saved";
		}

		bud.setRemaining(bud.getLimits() - bud.getSpent());

		budRepo.save(bud);

		return "saved";

	}

	public BudgetEntity getBudget(String bud, Month month) {

		BudgetEntity response = new BudgetEntity();
		response.setId(0);
		try {
			response = budRepo.findByCategory(bud, month.getValue());
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
		response.setMonth(month);
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

	public List<BudgetEntity> getBudgetList() {
		return budRepo.findAll();
	}

	public String updateBudgetSpent(UpdateBudgetDTO request) {

		BudgetEntity bud = new BudgetEntity();
		bud = getBudget(request.getCategory(), request.getMonth());
		Double updatedSpent = bud.getSpent() + request.getSpent();
		bud.setSpent(updatedSpent);
		bud.setRemaining(bud.getLimits() - updatedSpent);
		bud = budRepo.save(bud);
		return "spent updated";
	}

}
