package com.expense.tracker.category.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.category.entity.CategoryEntity;
import com.expense.tracker.category.service.CateService;

@RestController
@RequestMapping("/expense/category")
public class CategoryController {

	@Autowired
	CateService catServ;

	@PostMapping("/add")
	ResponseEntity<String> saveCategory(@RequestBody List<CategoryEntity> categories) {

		String response = catServ.saveCat(categories);

		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

	@GetMapping("/get")
	ResponseEntity<List<CategoryEntity>> getAllCategory() {

		List<CategoryEntity> categories = catServ.findAllCat();
		return ResponseEntity.status(HttpStatus.OK).body(categories);
	}

}
