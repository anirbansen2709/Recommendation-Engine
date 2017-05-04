package com.gamma.dexter.musicRecommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 * Created by Debashish Sen on 18-Apr-17.
 */

public class UserLoginDb {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/music";
    static final String USER = "root";
    static final String PASS = "root";
    private static UserLoginDb instance = null;
     public static UserLoginDb getInstance() {

        if (instance == null) {
            instance = new UserLoginDb();
        }
        return instance;
    }

    public boolean verifyUserIdPasswordCombination(UserLoginModel userLoginModel){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String userName = userLoginModel.getEmailId();
            String password = userLoginModel.getPassword();
            String sql = "select * from users where emailId = "+'"'+userName+'"'+" and password ="+'"'+password+'"'+"";

            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                System.out.println("No matching combination of userId and password");
                return false;
            }
            else {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Error at UserLoginDb verifyUserIdPasswordCombination" + e);
            return false;
        }
    }

    public boolean insertUser(UserSignupModel userSignupModel){
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();
            String sql;
            int resultSet;
            Statement stmt1 = con.createStatement();
            String firstName= userSignupModel.getFirstName();
            String lastName= userSignupModel.getLastName();
            String emailId= userSignupModel.getEmailId();
            String password= userSignupModel.getPassword();
            String sql1= "select * from users where emailId = "+'"'+emailId+'"'+"";
            ResultSet rs1=stmt1.executeQuery(sql1);
            //Checking for unique emailId before inserting into db
            if (!rs1.next()) {
                sql= "INSERT INTO users (firstName,lastName,emailId,password)\n" +
                        "VALUES ("+'"'+firstName+'"'+','+'"'+lastName+'"'+','+'"'+emailId+'"'+','+'"'+password+'"'+")";
                resultSet = stmt.executeUpdate(sql);
                if (resultSet>0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("emailId Already Exist");
                return false;
            }
        } catch (Exception e){
            System.out.println("Error at UserLoginDb insertUser"+e);
            return false;
        }
    }

    public int getUserIdByEmail(UserLoginModel userLoginModel) {

        return getUserIdByEmail(userLoginModel.getEmailId());
    }

    public int getUserIdByEmail(String email) {
        int userId=-1;
        try {

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "select userId from users where emailId = " + '"' + email + '"' + "";

            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
                userId = rs.getInt(1);
            else
                throw new Exception("UserId not found");
            rs.close();
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {

            System.out.println("Error at UserLoginDb getUserId" + e);
        }
        return userId;
    }
    public String getNameByEmail(String email) {
        String firstName=null,lastName = null;
        try {

            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "select firstName,lastName from users where emailId = " + '"' + email + '"' + "";

            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
            }
            else
                throw new Exception("UserId not found");
            rs.close();
            stmt.close();
            con.close();
        }
        catch (Exception e)
        {

            System.out.println("Error at UserLoginDb getUserId" + e);
        }
        return firstName+lastName;
    }
}
