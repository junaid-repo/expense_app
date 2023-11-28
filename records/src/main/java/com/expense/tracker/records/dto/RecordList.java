package com.expense.tracker.records.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class RecordList {
	private LocalDate date;
	private List<RecordHistoryDto> details;

}
