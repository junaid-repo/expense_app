package com.expense.tracker.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.tracker.category.entity.CategoryEntity;
import com.expense.tracker.category.repository.CategorySaveRepository;

@Service
public class CateService {

	@Autowired
	CategorySaveRepository catRepo;

	public String saveCat(List<CategoryEntity> categories) {

		catRepo.saveAll(categories);

		return "saved";

	}

	public List<CategoryEntity> findAllCat() {
		return catRepo.findAll();
	}

	public CategoryEntity findCategory(String categoryName) {
		CategoryEntity ce = new CategoryEntity();
		try {
			ce = catRepo.findById(categoryName).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			CategoryEntity ce2 = new CategoryEntity();
			return ce2;

		}
		return ce;
	}

}
