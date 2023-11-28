package com.expense.tracker.records.apis;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.expense.tracker.records.vo.AuthRequest;
import com.expense.tracker.records.vo.UpdateBudgetDTO;

@Component
public class RecordFacade {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	RecordServiceFeignClient rfc;

	Logger log = LoggerFactory.getLogger(this.getClass());

	public void updateSpent(UpdateBudgetDTO updateBud) {
		HttpEntity<UpdateBudgetDTO> request = new HttpEntity<>(updateBud, httpHeaders());
	}

	private HttpHeaders httpHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.setBearerAuth(getJwtToken());
		return httpHeaders;
	}

	public String getJwtToken() {
		AuthRequest auth = new AuthRequest();
		auth.setUsername("nana1");
		auth.setPassword("na");
		String token = rfc.generateToken(auth).getBody();
		log.info("The generated token in RecordFacade class is-->>" + token);
		return token;

	}

}
