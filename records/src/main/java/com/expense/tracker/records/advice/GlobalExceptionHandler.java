package com.expense.tracker.records.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidRequestData.class)
	ResponseEntity<Map<String, Object>> invalidRecordSearchRequestData(InvalidRequestData ex) {
		Map<String, Object> exMap = new HashMap<>();

		exMap.put("errCode", ex.errorCode);
		exMap.put("errMsg", ex.errorDesc);
		exMap.put("HttpStatus", HttpStatus.BAD_REQUEST.getReasonPhrase());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exMap);

	}

}
