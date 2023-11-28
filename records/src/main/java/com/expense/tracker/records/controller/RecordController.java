package com.expense.tracker.records.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.service.RecordService;

@RestController
@RequestMapping("/expense/record")
public class RecordController {
	
	@Autowired
	RecordService serv;
	
	@PostMapping("/add")
	ResponseEntity<String> addRecord(@RequestBody RecordEntity records){
		String response=serv.addRecord(records);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
