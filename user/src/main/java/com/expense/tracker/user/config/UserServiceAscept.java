package com.expense.tracker.user.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAscept {
	@Before(value = "execution(* com.expense.tracker.user.controller..*(..))")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("Before userserviceaspect >>>>>>>>>>>>>>>>>");
	}
	@Before(value = "execution(* com.expense.tracker.user.controller..*(..))")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("After userserviceaspect >>>>>>>>>>>>>>>>>");
	}
	@Before(value = "execution(* com.expense.tracker.user.controller..*(..))")
	public void afterReturn(JoinPoint joinPoint) {
		System.out.println("After Return userserviceaspect >>>>>>>>>>>>>>>>>");
	}
	@Around(value = "execution(* com.expense.tracker.user.controller..*(..))")
	public void aroundAdvice(JoinPoint joinPoint) {
		System.out.println("Around userserviceaspect >>>>>>>>>>>>>>>>>");
		
		//joinPoint.proceed();
	}
}
