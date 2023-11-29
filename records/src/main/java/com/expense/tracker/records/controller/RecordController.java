package com.expense.tracker.records.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.tracker.records.advice.InvalidRequestData;
import com.expense.tracker.records.dto.RecordList;
import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.service.RecordService;

@RestController
@RequestMapping("/expense/record")
public class RecordController {

	@Autowired
	RecordService serv;

	@PostMapping("/add")
	ResponseEntity<String> addRecord(@RequestBody RecordEntity records) throws InvalidRequestData {
		String response = serv.addRecord(records);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/{month}/{year}")
	ResponseEntity<List<RecordList>> getRecordHistory(@PathVariable String month, @PathVariable String year) {
		List<RecordList> rList = new ArrayList<>();

		rList = serv.getRecordHistory(month, year);

		return ResponseEntity.status(HttpStatus.OK).body(rList);

	}
}
