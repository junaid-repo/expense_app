package com.expense.tracker.budgets.dto;

import lombok.Data;

@Data
public class UpdateBudgetDTO {
private String category;
private Double limits;
private Double spent;
private Double remaining;
}
