/*
 * *********************************************************************************************
 * Copyright (C) 2016 Gamma Analytics, Inc. All rights reserved.                                *
 * http://www.gammanalytics.com/                                                                *
 * ---------------------------------------------------------------------------------------------*
 * The software in this package is published under the terms of the EUL (End User license)      *
 * agreement a copy of which has been included with this distribution in the license.txt file.  *
 * *********************************************************************************************
 */

package com.gamma.dexter.auth;

import com.gamma.dexter.db.mysql.helper.DatasourcePool;
import com.gamma.dexter.exception.AppAuthenticationException;
import com.gamma.dexter.exception.AppAuthorizationException;
import com.gamma.dexter.exception.AppUnknownAccountException;
import com.gamma.dexter.utility.CipherUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class MovieRecommendationRealm extends JdbcRealm {

	private Logger logger = LoggerFactory.getLogger(MovieRecommendationRealm.class);

	private String appAuthenticationQuery = "select password from users where emailId = ?";

	private String appUserRolesQuery = "SELECT name role_name FROM  user_role ur, role r where ur.role_id=r.id AND ur.username = ?";

	private String appPermissionQuery = "select permission from roles_permissions where role_name = ?";

	private DatasourcePool pool;

	public MovieRecommendationRealm() {
		pool = DatasourcePool.instance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthenticationInfo(org.apache
	 * .shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo info = null;
		Connection conn = null;

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}

		try {
			conn = pool.getConnection();

			String password = null;

			password = CipherUtil.decrypt(getPasswordForSpUser(conn, username));
			// String pass = CipherUtil.encrypt(upToken.getPassword());

			if (password == null) {
				throw new AppUnknownAccountException(
						"No account found for user [" + username + "]");
			}

			info = new SimpleAuthenticationInfo(username,
					password.toCharArray(), getName());

		} catch (SQLException e) {
			final String message = "There was a SQL error while authenticating user ["
					+ username + "]";
			if (logger.isErrorEnabled()) {
				logger.error(message, e);
			}

			// Rethrow any SQL errors as an authentication exception
			throw new AppAuthenticationException(message, e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return info;
	}

	/**
	 * Gets the password for sp user.
	 *
	 * @param conn
	 *            the conn
	 * @param username
	 *            the username
	 * @return the password for sp user
	 * @throws SQLException
	 *             the SQL exception
	 */
	private String getPasswordForSpUser(Connection conn, String username)
			throws SQLException {
		String result = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(appAuthenticationQuery);
			ps.setString(1, username);

			// Execute query
			rs = ps.executeQuery();

			// Loop over results - although we are only expecting one result,
			// since usernames should be unique
			boolean foundResult = false;
			while (rs.next()) {

				// Check to ensure only one row is processed
				if (foundResult) {
					throw new AppAuthenticationException(
							"USERNAMES_MUST_BE_UNIQUE " + username);
				}

				result = rs.getString(1);

				foundResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.jdbc.JdbcRealm#doGetAuthorizationInfo(org.apache
	 * .shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo info = null;
		if (principals == null) {
			throw new AppAuthorizationException(
					"PrincipalCollection method argument cannot be null.");
		}

		String username = (String) getAvailablePrincipal(principals);

		Connection conn = null;
		Set<String> roleNames = null;
		Set<String> permissions = null;
		try {
			conn = pool.getConnection();
			roleNames = getRoleNamesForUser(conn, username);
			if (permissionsLookupEnabled) {
				permissions = getPermissions(conn, username, roleNames);
			}

		} catch (SQLException e) {
			final String message = "There was a SQL error while authorizing user ["
					+ username + "]";
			if (logger.isErrorEnabled()) {
				logger.error(message, e);
			}

			// Rethrow any SQL errors as an authorization exception
			throw new AppAuthorizationException(message, e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.jdbc.JdbcRealm#getRoleNamesForUser(java.sql.Connection
	 * , java.lang.String)
	 */
	@Override
	protected Set<String> getRoleNamesForUser(Connection conn, String username)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<String> roleNames = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(appUserRolesQuery);
			ps.setString(1, username);

			// Execute query
			rs = ps.executeQuery();

			// Loop over results and add each returned role to a set
			while (rs.next()) {

				String roleName = rs.getString(1);

				// Add the role to the list of names if it isn't null
				if (roleName != null) {
					roleNames.add(roleName);
				} else {
					if (logger.isWarnEnabled()) {
						logger.warn("Null role name found while retrieving role names for user ["
								+ username + "]");
					}
				}
			}
		} finally {
			rs.close();
			ps.close();
		}
		return roleNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.shiro.realm.jdbc.JdbcRealm#getPermissions(java.sql.Connection,
	 * java.lang.String, java.util.Collection)
	 */
	@Override
	protected Set<String> getPermissions(Connection conn, String username,
			Collection<String> roleNames) throws SQLException {
		PreparedStatement ps = null;
		Set<String> permissions = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(appPermissionQuery);
			for (String roleName : roleNames) {

				ps.setString(1, roleName);

				ResultSet rs = null;

				try {
					// Execute query
					rs = ps.executeQuery();

					// Loop over results and add each returned role to a set
					while (rs.next()) {

						String permissionString = rs.getString(1);

						// Add the permission to the set of permissions
						permissions.add(permissionString);
					}
				} finally {
					JdbcUtils.closeResultSet(rs);
				}

			}
		} finally {
			ps.close();
		}

		return permissions;
	}
}
