package com.expense.tracker.records.vo;

import java.time.Month;

import lombok.Data;

@Data
public class UpdateBudgetDTO {
	private String category;
	private Double limits;
	private Double spent;
	private Double remaining;
	private Month month;
}
