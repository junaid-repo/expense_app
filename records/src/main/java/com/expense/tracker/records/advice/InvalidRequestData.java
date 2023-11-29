package com.expense.tracker.records.advice;

public class InvalidRequestData extends Exception {

	String errorCode;
	String errorDesc;

	public InvalidRequestData(String retMsg, String retCode) {
		this.errorDesc = retMsg;
		this.errorCode = retCode;
	}

}
