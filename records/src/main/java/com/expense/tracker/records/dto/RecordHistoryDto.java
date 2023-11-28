package com.expense.tracker.records.dto;

import lombok.Data;

@Data
public class RecordHistoryDto {
	private String category;
	private String account;
	private Double amount;

}
