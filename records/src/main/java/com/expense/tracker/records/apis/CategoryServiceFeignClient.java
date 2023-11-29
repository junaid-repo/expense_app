package com.expense.tracker.records.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.expense.tracker.records.vo.CategoryEntity;



@FeignClient(name="CATEGORY-SERVICE")
public interface CategoryServiceFeignClient {
	
	@GetMapping("/expense/category/get/{categoryName}")
	ResponseEntity<CategoryEntity> searchCategory(@PathVariable String categoryName);

}
