/*
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.
 * http://www.gammanalytics.com/
 * --------------------------------------------------------------------------------------------
 * The software in this package is published under the terms of the EUL (End User license)     
 * agreement a copy of which has been included with this distribution in the license.txt file.
 */

package com.gamma.dexter.db.mysql.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.gamma.dexter.config.DatabaseProperties;

public class DatasourcePool {
	private static DatasourcePool datasource;
	private static DatabaseProperties lib = DatabaseProperties.instance();
	private static String host = lib.getHost();
	private static String port = lib.getPort();
	private static String db = lib.getDatabase();
	private static String user = lib.getUsername();
	private static String pass = lib.getPassword();

		/** The log. */
	
		/**0
		 * Instantiates a new datasource.
		 * @throws ClassNotFoundException 
		 * 
		 * @throws IOException
		 *             Signals that an I/O exception has occurred.
		 * @throws SQLException
		 *             the sQL exception
		 */
		private DatasourcePool() throws ClassNotFoundException {
			Class.forName("com.mysql.jdbc.Driver");
		}
	
		/**
		 * Gets the single instance of Datasource.
		 * 
		 * @return single instance of Datasource
		 */
		public static synchronized DatasourcePool instance() {
			if (datasource == null) {
				try {
					datasource = new DatasourcePool();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	
			}
			return datasource;
		}
	
		/**
		 * Gets the connection.
		 * 
		 * @return the connection
		 * @throws SQLException
		 *             the sQL exception
		 */
		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db,
					user, pass);
		}
}

