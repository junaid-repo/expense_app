package com.expense.tracker.budgets.api;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.expense.tracker.budgets.vo.AuthRequest;
import com.expense.tracker.budgets.vo.CategoryEntity;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BudgetFacade {

	@Autowired
	UserServiceClient ufc;

	@Autowired
	RestTemplate restTemplate;

	public boolean getCategoryDetails(String categoryName) {

		HttpEntity<String> request = new HttpEntity(categoryName, httpHeaders());
		String uri = "http://" + "CATEGORY-SERVICE" + "/expense/category/get/" + categoryName;

		ResponseEntity<CategoryEntity> response = restTemplate.exchange(uri, HttpMethod.GET, request,
				CategoryEntity.class);
		log.info("the response of getCategory call is--> " + response.getBody());
		if (response.getBody().getName() == null) {

			return false;
		}
		return true;
	}

	public HttpHeaders httpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setBearerAuth(getJwtToken());
		return httpHeaders;
	}

	private String getJwtToken() {
		AuthRequest auth = new AuthRequest();
		auth.setUsername("nana3");
		auth.setPassword("na");

		String token = ufc.generateToken(auth).getBody();

		return token;
	}

}
