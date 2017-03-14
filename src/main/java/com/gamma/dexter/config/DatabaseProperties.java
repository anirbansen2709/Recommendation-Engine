/*
 * *********************************************************************************************
 * Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dexter.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DatabaseProperties {

	private static DatabaseProperties prop;
	Properties props = new Properties();
	public static synchronized DatabaseProperties instance(){
		if(prop == null){
			prop = new DatabaseProperties();
		}
		return prop;
	}
	
	private DatabaseProperties() {
		String resourceName = "database.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			props.load(resourceStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Gets the Host.
	 * 
	 * @return the host
	 */
	public String getHost() {
		return props.getProperty("host");
	}


	
	/**
	 * Gets the database.
	 * 
	 * @return the database
	 */
	public String getDatabase() {
		return props.getProperty("database");
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return props.getProperty("username");
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return props.getProperty("password");
	}

	/**
	 * Gets the port.
	 * 
	 * @return the port
	 */
	public String getPort() {
		return props.getProperty("port");
	}

	/**
	 * Gets the driver class.
	 * 
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return props.getProperty("driverClass");
	}

	/**
	 * Gets the acquire increment.
	 * 
	 * @return the acquireIncrement
	 */
	public String getAcquireIncrement() {
		return props.getProperty("acquireIncrement");
	}

	/**
	 * Gets the initial pool size.
	 * 
	 * @return the initialPoolSize
	 */
	public String getInitialPoolSize() {
		return props.getProperty("initialPoolSize");
	}

	/**
	 * Gets the max pool size.
	 * 
	 * @return the maxPoolSize
	 */
	public String getMaxPoolSize() {
		return props.getProperty("maxPoolSize");
	}

	/**
	 * Gets the min pool size.
	 * 
	 * @return the minPoolSize
	 */
	public String getMinPoolSize() {
		return props.getProperty("minPoolSize");
	}

	/**
	 * Gets the max statements.
	 * 
	 * @return the maxStatements
	 */
	public String getMaxStatements() {
		return props.getProperty("maxStatements");
	}

	/**
	 * Gets the partition count.
	 * 
	 * @return the partition count
	 */
	public String getPartitionCount() {
		return props.getProperty("partitionCount");
	}

	public String getStatementsCacheSize() {
		return props.getProperty("statementsCacheSize");
	}

	public String getMinConnectionsPerPartition() {
		return props.getProperty("minConnectionsPerPartition");
	}

	public String getMaxConnectionsPerPartition() {
		return props.getProperty("maxConnectionsPerPartition");
	}

	public String getIdleMaxAgeInMinutes() {
		return props.getProperty("idleMaxAgeInMinutes");
	}

	public String getIdleConnectionTestPeriodInMinutes() {
		return props.getProperty("idleConnectionTestPeriodInMinutes");
	}
}
