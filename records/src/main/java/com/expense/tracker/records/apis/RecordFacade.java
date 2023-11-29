package com.expense.tracker.records.apis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.expense.tracker.records.advice.InvalidRequestData;
import com.expense.tracker.records.vo.AuthRequest;
import com.expense.tracker.records.vo.CategoryEntity;
import com.expense.tracker.records.vo.UpdateBudgetDTO;

@Component
public class RecordFacade {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RecordServiceFeignClient rfc;

	@Autowired
	CategoryServiceFeignClient cfc;

	Logger log = LoggerFactory.getLogger(this.getClass());

	public void updateSpent(UpdateBudgetDTO updateBud) {
		HttpEntity<UpdateBudgetDTO> request = new HttpEntity<>(updateBud, httpHeaders());
		String uri = "http://" + "BUDGETS-SERVICE" + "/expense/budgets/update";
		ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
	}

	private HttpHeaders httpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setBearerAuth(getJwtToken());
		return httpHeaders;
	}

	public String getJwtToken() {
		AuthRequest auth = new AuthRequest();
		auth.setUsername("nana3");
		auth.setPassword("na");
		String token = rfc.generateToken(auth).getBody();
		log.info("The generated token in RecordFacade class is-->>" + token);
		return token;

	}

	public CategoryEntity findCategoryDetails(String category) throws InvalidRequestData {
		log.info("Inside RecordFacade class with category --> " + category);
		CategoryEntity response = new CategoryEntity();

		response = cfc.searchCategory(category).getBody();

		if (response.getName() == null)
			throw new InvalidRequestData("category is not valid", "400");

		return response;
	}

}
