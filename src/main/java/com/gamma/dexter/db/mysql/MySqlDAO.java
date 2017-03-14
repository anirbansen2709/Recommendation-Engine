/*
 * **********************************************************************************************
 *  Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dexter.db.mysql;

import java.util.List;
import java.util.Map;

import com.gamma.dexter.db.wrapper.BaseDAO;

public interface MySqlDAO extends BaseDAO {
	
    void update(String query);
	List<Map<String, Object>> select(String query);
	void insert(String query);
	void delete(String query);
}
