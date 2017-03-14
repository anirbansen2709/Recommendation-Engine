/*
 * **********************************************************************************************
 *  Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dexter.auth;


import com.gamma.dexter.admin.UserModel;
import com.gamma.dexter.db.DAOFactory;
import com.gamma.dexter.db.mysql.MySqlDAO;
import com.gamma.dexter.exception.AppAccountException;
import com.gamma.dexter.exception.AppAuthenticationException;
import com.gamma.dexter.utility.CipherUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
//import com.gamma.um.utility.ModuleProperties;

/**
 * The Class LoginHandler.
 *
 * @author abhijit
 */
public class LoginHandler {

	/** The dao. */
	private MySqlDAO dao;

	/** The logger. */
	private Logger logger = LoggerFactory.getLogger(LoginHandler.class);

	/**
	 * Instantiates a new login my sql impl.
	 */
	public LoginHandler() {
		DAOFactory<MySqlDAO> factory = DAOFactory.getInstance(MySqlDAO.class);
		dao = (MySqlDAO) factory.getDAO();
	}

	/* (non-Javadoc)
	 * @see com.gamma.churn.app.handler.impl.abs.ILoginImpl#validate(com.gamma.churn.app.model.LoginModel)
	 */
	public boolean validate(LoginModel loginModel) {
		String username = loginModel.getUserId();
		String password = CipherUtil.encrypt(loginModel.getPasswd());

		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
		} catch (AppAuthenticationException e) {
			logger.info("The username or password was not correct");
		}
		if (currentUser.isAuthenticated()) {
			return true;
		}
		return false;


	}

	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 *//*
	private boolean isValid(Date validDate) {
        if (validDate.after(new Date())) {
                return true;
        }
        logger.info("Your Validity Peroid is Over");
        return false;
	}*/

	public boolean isActivated(String userId){
		String query = "select activated from users where username='"+userId+"'";

		List<Map<String, Object>> response = dao.select(query);
		for(Map<String,	Object> r : response){
			return (Boolean) r.get("activated");
		}
		return false;
	}

	public boolean reset(String password) {
		Subject currentUser = SecurityUtils.getSubject();
		String userName = (String) currentUser.getPrincipal();
		String query = "update users set  password='"+CipherUtil.encrypt(password)+"' where username='"+userName+"'";
		String updateQuery = "update users set  activated="+Boolean.TRUE+" where username='"+userName+"'";
		DAOFactory<MySqlDAO> factory = DAOFactory.getInstance(MySqlDAO.class);
		MySqlDAO dao = (MySqlDAO) factory.getDAO();
		try{
			dao.update(query);
		}catch(Exception e){
			return false;
		}
		try{
			dao.update(updateQuery);
		}catch(Exception e){
			return false;
		}

		return true;
	}

	public void changePassword(String oldPassword, String newPassword) throws AppAccountException {
		Subject currentUser = SecurityUtils.getSubject();
		String userName = (String) currentUser.getPrincipal();
		if(isPasswordMatches(userName, oldPassword)){
			String query = "update users set  password='"+CipherUtil.encrypt(newPassword)+"' where username='"+userName+"'";
			dao.update(query);
		}else{
			throw new AppAccountException(" Entered Password  is not valid for user "+userName);
		}
	}

	private boolean isPasswordMatches(String userName, String oldPassword) {
		StringBuilder query = new StringBuilder("select password from users where username='").append(userName).append("'");
		List<Map<String, Object>> result  = dao.select(query.toString());
		String dbPassword = null;
		for(Map<String, Object> r : result){
			dbPassword = (String) r.get("password");
		}
		return dbPassword.equals(CipherUtil.encrypt(oldPassword));
	}

	public void emailPassword(String userId) throws AppAccountException {
		try{
			UserModel model = getUserModelByEmailId(userId);
//			MailUtility.sendPasswordInMail(model);
		}catch(AppAccountException e){
			throw new AppAccountException("Sorry We won't be able proceed further, No Email Id is not registered against your username");
		}
	}

	private UserModel getUserModelByEmailId(String userId) {
		StringBuilder query = new StringBuilder("select * from users where username='").append(userId.trim()).append("'");
		List<Map<String, Object>> result  = dao.select(query.toString());
		boolean foundResult = false;
		UserModel model = new UserModel();
		for(Map<String, Object> r : result){
			if(!foundResult){
				String name = (String)r.get("username");
				String email = (String)r.get("email");
				String password = (String)r.get("password");
				String id = String.valueOf((Long) r.get("id"));
				model.setId(id);
				model.setUserid(name);
				model.setEmail(email);
				model.setPassword(password);
				foundResult = true;
			}
		}
		if(model.getEmail() == null) throw new AppAccountException();
		return model;
	}

	public static void main(String[] args) {
        String query = "select activated from users where username='"+"admin"+"'";
        DAOFactory<MySqlDAO> factory = DAOFactory.getInstance(MySqlDAO.class);
        MySqlDAO dao = (MySqlDAO) factory.getDAO();

        List<Map<String, Object>> response = dao.select(query);
        for(Map<String,	Object> r : response){
            System.out.println((Boolean) r.get("activated"));
        }
	}

}

