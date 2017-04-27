package com.gamma.dexter.musicRecommendation;

/**
 * Created by Anirban on 26-Apr-17.
 */
public class UserContext {
    int userId;
    private static UserContext instance=null;
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
//        session.setAttribute("userId",userId);
    }
    public static UserContext getInstance(){
        if(instance==null){
            instance = new UserContext();
        }
        return instance;
    }
}