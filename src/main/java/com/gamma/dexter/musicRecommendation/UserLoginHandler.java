package com.gamma.dexter.musicRecommendation;


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
        return userLoginServices.userLogin(userLoginModel);
    }

    public boolean insertUser(UserSignupModel userSignupModel) {return userLoginServices.insertUser(userSignupModel);}

    public int getUserId(UserLoginModel userLoginModel) {return userLoginServices.getUserId(userLoginModel);}

}
