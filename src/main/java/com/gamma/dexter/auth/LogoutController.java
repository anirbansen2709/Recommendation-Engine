/*
 *  * **********************************************************************************************
 *  Copyright (C) 2015 Gamma Analytics, Inc. All rights reserved.                               *
 *  http://www.gammanalytics.com/                                                               *
 *  --------------------------------------------------------------------------------------------*
 *  The software in this package is published under the terms of the EUL (End User license)     *
 *  agreement a copy of which has been included with this distribution in the license.txt file. *
 * **********************************************************************************************
 */
package com.gamma.dexter.auth;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class LogoutController.
 *
 * @author abhijit
 */

@Controller
public class LogoutController {


	/**
	 * Logout.
	 *
	 * @param request the request
	 * @param response the response
	 * @return the model and view
	 */
//	@RequestMapping(value = "/logout")
//	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
//		request.getSession().invalidate();
//		return new ModelAndView("login");
//	}

	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
//		request.getSession().invalidate();
		Subject currentSubject = SecurityUtils.getSubject();
		currentSubject.logout();
		return new ModelAndView("login");
	}

}
