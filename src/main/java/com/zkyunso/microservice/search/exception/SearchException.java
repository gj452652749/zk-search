package com.zkyunso.microservice.search.exception;

import org.springframework.core.NestedRuntimeException;

public class SearchException extends NestedRuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchException(String msg, Throwable cause) {
		super(msg, cause);
		// TODO Auto-generated constructor stub
	}

}
