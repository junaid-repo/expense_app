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

@Table(name = "records")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private LocalDateTime createdDate = LocalDateTime.now();
	private LocalDate date = LocalDate.now();

	private LocalTime time = LocalTime.now();
	private String type;
	private String account;
	private String category;
	private String notes;
	private Double amount;
}
