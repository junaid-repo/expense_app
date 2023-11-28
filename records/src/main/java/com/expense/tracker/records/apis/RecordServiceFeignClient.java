package com.expense.tracker.records.apis;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.expense.tracker.records.vo.AuthRequest;



@FeignClient(name="USER-SERVICE")
public interface RecordServiceFeignClient {
	
	@PostMapping("/expense/user/auth/generateToken")
	ResponseEntity<String> generateToken(@RequestBody AuthRequest creds);

}
