package com.expense.tracker.budgets.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.expense.tracker.budgets.vo.AuthRequest;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {
	@PostMapping("/expense/user/auth/generateToken")
	ResponseEntity<String> generateToken(@RequestBody AuthRequest creds);
}
