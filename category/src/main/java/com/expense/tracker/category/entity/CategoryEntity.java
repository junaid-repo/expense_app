package com.expense.tracker.category.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "category")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO) private Integer id;
	 */
	@Id
	private String name;

	private LocalDateTime createdDate = LocalDateTime.now();

	private String type;

}
