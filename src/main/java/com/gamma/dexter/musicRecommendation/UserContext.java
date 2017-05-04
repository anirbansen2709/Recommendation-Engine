package com.gamma.dexter.musicRecommendation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by Anirban on 26-Apr-17.
 */
public class UserContext {

    private static UserContext instance = null;

    public static UserContext instance() {

        if (instance == null) {
            instance = new UserContext();
        }
        return instance;
    }

    public int getUserId() {

        return UserLoginServices.instanceUserLoginServices().getUserIdByEmail(getUsername());
    }
    public String getName() {

        return UserLoginServices.instanceUserLoginServices().getNameByEmail(getUsername());
    }


    public String getUsername() {
        Subject currentUser = SecurityUtils.getSubject();
        String username = currentUser.getPrincipal().toString();
        return username;
    }
}