package com.expense.tracker.records.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "recordsHistory")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDate date;
	private String recordIds;
	private LocalDateTime createdDate = LocalDateTime.now();

}
