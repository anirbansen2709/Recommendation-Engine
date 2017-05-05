package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.utility.CipherUtil;

/**
 * Created by Debashish Sen on 18-Apr-17.
 */
public class UserLoginServices {
    UserLoginDb userLoginDb = UserLoginDb.getInstance();
    private static UserLoginServices  instance = null;

    public static UserLoginServices  instanceUserLoginServices() {
        if (instance == null) {
            instance = new UserLoginServices();
        }
        return instance;
    }

    public boolean userLogin(UserLoginModel userLoginModel) {
        String username = userLoginModel.getEmailId();
        String password = CipherUtil.encrypt(userLoginModel.getPassword());

        UserLoginModel userLoginModelWithEncryptedPassword=new UserLoginModel();
        userLoginModelWithEncryptedPassword.setEmailId(username);
        userLoginModelWithEncryptedPassword.setPassword(password);

        if (verifyUserIdPasswordCombination(userLoginModelWithEncryptedPassword)) {
            return true;
        }
        System.out.println("The username or password was not correct");
        return false;
    }
    public boolean insertUser(UserSignupModel userSignupModel){
        String username = userSignupModel.getEmailId();
        String password = CipherUtil.encrypt(userSignupModel.getPassword());
        String firstName = userSignupModel.getFirstName();
        String lastName = userSignupModel.getLastName();

        UserSignupModel userLoginModelWithEncryptedPassword=new UserSignupModel();
        userLoginModelWithEncryptedPassword.setEmailId(username);
        userLoginModelWithEncryptedPassword.setPassword(password);
        userLoginModelWithEncryptedPassword.setFirstName(firstName);
        userLoginModelWithEncryptedPassword.setLastName(lastName);

        return userLoginDb.insertUser(userLoginModelWithEncryptedPassword);

    }


    private boolean verifyUserIdPasswordCombination(UserLoginModel userLoginModel) {
        return userLoginDb.verifyUserIdPasswordCombination(userLoginModel);
    }
    public int getUserId(UserLoginModel userLoginModel){
        return userLoginDb.getUserIdByEmail(userLoginModel);
    }
    public int getUserIdByEmail(String email){
        return userLoginDb.getUserIdByEmail(email);
    }
    public String getNameByEmail(String email){
        return userLoginDb.getNameByEmail(email);
    }

    public static void main(String[] args) {
        UserLoginServices.instanceUserLoginServices().getUserIdByEmail("as@gmail.com");
    }
}
