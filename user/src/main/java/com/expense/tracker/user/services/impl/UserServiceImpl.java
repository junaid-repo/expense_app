package com.expense.tracker.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.expense.tracker.user.dto.AuthRequest;
import com.expense.tracker.user.dto.RegisterResponse;
import com.expense.tracker.user.entity.UserCredentials;
import com.expense.tracker.user.repository.UserSaveRepository;
import com.expense.tracker.user.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserSaveRepository userRegRepo;

	@Autowired
	PasswordEncoder psdEnocder;

	@Autowired
	JwtService jwtService;

	@Override
	public RegisterResponse registerUser(UserCredentials user) {
		user.setPassword(psdEnocder.encode(user.getPassword()));
		UserCredentials out = new UserCredentials();
		RegisterResponse response = new RegisterResponse();
		String username = "";

		out = userRegRepo.save(user);

		if (out.getId() > 0) {
			username = user.getName().replaceAll("\\s", "").toLowerCase() + String.valueOf(out.getId());
		}

		out.setUsername(username);

		out = userRegRepo.save(out);

		response.setReturnCode("201");
		response.setReturnMsg("User Created");
		response.setUsername(username);
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	public String generateToken(AuthRequest creds) {
		// TODO Auto-generated method stub
		return jwtService.generateToken(creds.getUsername());
	}

	@Override
	public void validateToken(String token) {
		// TODO Auto-generated method stub
		 jwtService.validateToken(token);
	
	}
}
