package com.expense.tracker.user.services;

import com.expense.tracker.user.dto.AuthRequest;
import com.expense.tracker.user.dto.RegisterResponse;
import com.expense.tracker.user.entity.UserCredentials;

public interface UserService {

	RegisterResponse registerUser(UserCredentials creds);

	String generateToken(AuthRequest creds);

	void validateToken(String token);

}
