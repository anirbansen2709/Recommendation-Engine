/*
 * *********************************************************************************************
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dexter.admin;

import com.gamma.dexter.db.DAOFactory;
import com.gamma.dexter.db.mysql.MySqlDAO;
import com.gamma.dexter.utility.CipherUtil;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Component
public class AdminHandler {
	MySqlDAO dao;

	public AdminHandler() {
		DAOFactory<MySqlDAO> factory = DAOFactory.getInstance(MySqlDAO.class);
		dao = (MySqlDAO) factory.getDAO();
	}

	public void createUser(UserModel user) {
		long id = System.currentTimeMillis();
		StringBuilder userQuery = new StringBuilder("insert into users(id, username, password, first_name, email, activated, valid) values(");
		userQuery.append(id).append(",");
		userQuery.append("'").append(user.getUserid()).append("',");
		userQuery.append("'").append(CipherUtil.encrypt(user.getPassword())).append("',");
		userQuery.append("'").append(user.getUsername()).append("',");
		String email = user.getEmail();
		if(email != null){
			userQuery.append("'").append(email).append("',");
		}else{
			userQuery.append("'").append("").append("',");
		}
		userQuery.append("").append(Boolean.FALSE).append(",");
		userQuery.append("'").append("2017-12-31").append("')");
		try {
			dao.insert(userQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder roleQuery = new StringBuilder("insert into user_role(user_id, role_id, username) values(");
		roleQuery.append(id).append(",");
		roleQuery.append("").append(getRoleIdFromRoleName(user.getRole())).append(",");
		roleQuery.append("'").append(user.getUserid()).append("')");
		try {
			dao.insert(roleQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getRoleIdFromRoleName(String role) {
		if (role.equalsIgnoreCase("admin")) {
			return 1;
		} else if (role.equalsIgnoreCase("super")) {
			return 2;
		} else if (role.equalsIgnoreCase("user")) {
			return 3;
		}
		return 0;
	}

	public void deleteUser(String userid) {
		StringBuilder userQuery = new StringBuilder("DELETE FROM users WHERE id=").append(userid);

		try {
			dao.delete(userQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder roleQuery = new StringBuilder("DELETE FROM user_role WHERE user_id=").append(userid);
		try {
			dao.delete(roleQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserModel> getUsers() {
		List<UserModel> data = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		sb.append("select u.id id, u.username userid, u.first_name username, r.name role  from users u LEFT JOIN user_role ur ON u.id=ur.user_id LEFT JOIN role r ON ur.role_id=r.id;");
		List<Map<String, Object>> response = dao.select(sb.toString());
		for (Map<String, Object> r : response) {
			UserModel model = new UserModel();
			model.setId(String.valueOf((Long) r.get("id")));
			model.setUserid((String) r.get("userid"));
			model.setUsername((String) r.get("username"));
			model.setRole((String)r.get("role"));
			data.add(model);
		}
		return data;
	}

	public void resetPassword(String userId, String passwordReset) {
		StringBuilder updateQuery = new StringBuilder("update users set password='").append(CipherUtil.encrypt(passwordReset))
				.append("', activated=").append(Boolean.FALSE).append(" ")
				.append("WHERE id=").append(userId);

		try {
			dao.update(updateQuery.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

