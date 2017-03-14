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

public class AppUnknownAccountException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6587101829994628943L;

	public AppUnknownAccountException() {
		super();
	}
	
	public AppUnknownAccountException(Throwable cause) {
		super(cause);
	}
	
	public AppUnknownAccountException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AppUnknownAccountException(String message) {
		super(message);
	}

}
