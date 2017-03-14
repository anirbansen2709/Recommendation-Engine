/*
 * *********************************************************************************************
 * Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dexter.admin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class AdminController {

	@Autowired
	AdminHandler handler = new AdminHandler();

	@RequestMapping(value = "/listUser", method = RequestMethod.GET)
	public @ResponseBody
	String getUserList() {
    	/*    				Subject subject = SecurityUtils.getSubject();
			if (!subject.hasRole("ROLE_ADMIN")) {
				return null;
			}
*/		JSONObject json = new JSONObject();
		String message = null;
		JSONArray array = new JSONArray();
		try {
			List<UserModel> users = handler.getUsers();
			for (UserModel m : users) {
				JSONObject obj = new JSONObject();
				obj.put("id", m.getId());
				obj.put("userid", m.getUserid());
				obj.put("username", m.getUsername());

				obj.put("role", m.getRole());
				array.add(obj);
			}
			message = "200";
		} catch (Exception e) {
			message = "404";
		}
		json.put("returnCode", message);
		json.put("payload", array);
		return json.toString();
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public  @ResponseBody
	String  createUser(@ModelAttribute("userDetails") UserModel userModel) {
		Subject subject = SecurityUtils.getSubject();
/*
		if (!subject.hasRole("ROLE_ADMIN")) {
			return null;
		}
*/
		JSONObject json = new JSONObject();
		try {
			handler.createUser(userModel);
			json.put("successMessage", "created");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorMessage", "Invalid Credentials");
		}
		return json.toString();
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public @ResponseBody
	String  deleteUser(@RequestParam String userid) {
		Subject subject = SecurityUtils.getSubject();
/*
		if (!subject.hasRole("ROLE_ADMIN")) {
			return null;
		}
*/
		JSONObject json = new JSONObject();
		try {
			handler.deleteUser(userid);
			json.put("successMessage", "Deleted User");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Need to raise an exception
			json.put("errorMessage", "Invalid Credentials");

		}
		return json.toString();
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody
	String  resetPassword(@RequestParam String user_id,@RequestParam String password_reset) {
		Subject subject = SecurityUtils.getSubject();

//		if (!subject.hasRole("ROLE_ADMIN")) {
//			return null;
//		}
		JSONObject json = new JSONObject();
		String message = null;
		try {
			//TODO Implement this
			handler.resetPassword(user_id,password_reset);
			json.put("successMessage", "Password Reseted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("errorMessage", "Invalid Credentials");
		}
		return json.toString();
	}

}
