package com.expense.tracker.records.dto;

import lombok.Data;

@Data
public class RecordDTO {

	private String type;
	private String account;
	private String category;
	private String notes;
	private String amount;

}
