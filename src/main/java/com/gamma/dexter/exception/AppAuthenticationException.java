/*
 * *********************************************************************************************
 * Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dexter.exception;


public class AppAuthenticationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4637405788091908962L;

	public AppAuthenticationException(){
		super();
	}
	
	public AppAuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppAuthenticationException(String message) {
		super(message);
	}
	
	public AppAuthenticationException(Throwable cause) {
		super(cause);
	}

}
