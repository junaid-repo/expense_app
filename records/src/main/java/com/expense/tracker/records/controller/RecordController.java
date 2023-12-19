package com.expense.tracker.records.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.expense.tracker.records.advice.InvalidRequestData;
import com.expense.tracker.records.dto.RecordList;
import com.expense.tracker.records.entity.RecordEntity;
import com.expense.tracker.records.service.RecordService;
import com.opencsv.exceptions.CsvException;

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

	@PostMapping("/add/csv")
	ResponseEntity<CompletableFuture<String>> addRecordFromCSV(@RequestParam MultipartFile file)
			throws IOException, CsvException {

		CompletableFuture<String> rows = serv.addRecordFromFile(file);

		return ResponseEntity.status(HttpStatus.OK).body(rows);

	}

}
