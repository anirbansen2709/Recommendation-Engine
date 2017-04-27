package com.gamma.dexter.musicRecommendation;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by Debashish Sen on 18-Apr-17.
 */
public class UserLoginHandler {
    UserLoginServices userLoginServices = UserLoginServices.instanceUserLoginServices();
    private static UserLoginHandler instance = null;

    public static UserLoginHandler instanceUserLoginHandler() {
        if (instance == null) {
            instance = new UserLoginHandler();
        }
        return instance;
    }

    public boolean userLogin(UserLoginModel userLoginModel){
        org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
        if ( !currentUser.isAuthenticated() ) {
            //collect user principals and credentials in a gui specific manner
            //such as username/password html form, X509 certificate, OpenID, etc.
            //We'll use the username/password example here since it is the most common.
            //(do you know what movie this is from? ;)
            UsernamePasswordToken token = new UsernamePasswordToken(userLoginModel.getEmailId(), userLoginModel.getPassword());
            //this is all you have to do to support 'remember me' (no config - built in!):
            token.setRememberMe(true);
            currentUser.login(token);
        }
        return currentUser.isAuthenticated();
//        return userLoginServices.userLogin(userLoginModel);
    }

    public boolean insertUser(UserSignupModel userSignupModel) {return userLoginServices.insertUser(userSignupModel);}

    public int getUserId(UserLoginModel userLoginModel) {return userLoginServices.getUserId(userLoginModel);}

}
