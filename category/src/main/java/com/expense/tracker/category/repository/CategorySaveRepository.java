package com.expense.tracker.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.tracker.category.entity.CategoryEntity;

public interface CategorySaveRepository extends JpaRepository<CategoryEntity, Integer >{

}
