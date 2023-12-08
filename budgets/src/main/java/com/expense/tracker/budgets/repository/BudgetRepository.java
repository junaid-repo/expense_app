package com.expense.tracker.budgets.repository;

import java.time.Month;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.tracker.budgets.entity.BudgetEntity;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Integer>{

	@Query(value="select * from budget_details bd where bd.category =?1 and bd.month=?2", nativeQuery=true)
	BudgetEntity findByCategory(String bud, int month);

	@Query(value="delete from budget_details bd where bd.category =?1", nativeQuery=true)
	void deleteByCategory(String category);

}
