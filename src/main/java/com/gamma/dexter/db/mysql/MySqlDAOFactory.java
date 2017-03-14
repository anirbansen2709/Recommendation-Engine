/*
 * **********************************************************************************************
 *  Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dexter.db.mysql;

import com.gamma.dexter.db.DAOFactory;
import com.gamma.dexter.db.wrapper.BaseDAO;

/**
 * A factory for creating MySqlDAO objects.
 * 
 * @author abhijit
 */
public class MySqlDAOFactory extends DAOFactory<BaseDAO> {

	private MySqlDAO dao;

	public MySqlDAOFactory() {
		dao = new MySqlDAOImpl();
	}

	@Override
	public MySqlDAO getDAO() {
		return dao;
	}

}
