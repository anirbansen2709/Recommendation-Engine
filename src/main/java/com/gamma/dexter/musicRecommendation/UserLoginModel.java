package com.gamma.dexter.musicRecommendation;

/**
 * Created by Debashish Sen on 18-Apr-17.
 */
public class UserLoginModel {
    private  String emailId,password;

    int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
