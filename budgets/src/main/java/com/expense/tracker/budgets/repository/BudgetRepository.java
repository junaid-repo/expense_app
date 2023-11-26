package com.expense.tracker.budgets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.budgets.entity.BudgetEntity;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Integer>{

}
