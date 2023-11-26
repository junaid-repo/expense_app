package com.expense.tracker.cloudgateway.filter;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	public static final List<String> openApiEndpoints = List.of(
			"/expense/user/auth/register",
			"/expense/user/auth/generateToken",
			"/expense/user/auth/validateToken",
			"/eureka");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
