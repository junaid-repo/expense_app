package com.expense.tracker.budgets.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "budgetDetails")
@AllArgsConstructor
@NoArgsConstructor
public class BudgetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private Month month = LocalDate.now().getMonth();
	private LocalDateTime createdDate = LocalDateTime.now();

	private String category;
	private Double limits;
	private Double spent;
	private Double remaining;

}
