package com.soham.libs.externalizedrules.poc.exceptions;

public class RuleNotInitialzedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 98774240365736856L;

	public RuleNotInitialzedException(String arg0,Exception e) {
		super(arg0,e);
		
	}

	public RuleNotInitialzedException(String arg0) {
		super(arg0);
	}

	
}
