package com.gamma.dexter.test;

import java.sql.*;
import java.util.*;

/**
 * Created by Anirban on 19/01/2017.
 */
public class TestDbModel {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";
    private static TestDbModel instance = null;
    Map<String, String> mapOfTopProcesses = new LinkedHashMap<String, String>();

    public static TestDbModel intance() {

        if (instance == null) {
            instance = new TestDbModel();
        }
        return instance;
    }
    public static void main(String[] args) {
        TestDbModel testDbModel = TestDbModel.intance();
        testDbModel.findTopUsages("memory", 4);

    }
    public List<TestUserModel> getData() {
        List<TestUserModel> users = new ArrayList<TestUserModel>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                TestUserModel singleUsers = new TestUserModel();
                singleUsers.setId(rs.getString("id"));
                singleUsers.setFirstName(rs.getString("first_name"));
                singleUsers.setLastName(rs.getString("last_name"));
                singleUsers.setUsername(rs.getString("username"));
                singleUsers.setPassword(rs.getString("password"));
                singleUsers.setValid(rs.getString("valid"));
                singleUsers.setActivated(rs.getString("activated"));
                singleUsers.setEmail(rs.getString("email"));
                users.add(singleUsers);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("createStatementError in getUsers()" + e);
        }
        return users;
    }
    public boolean isUserNameDuplicate(TestUserModel testUserModel) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String userName = testUserModel.getUsername();
            String sql = "SELECT * FROM users WHERE username=" + "'" + userName + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
            return false;
        }
    }

    public void insertUser(TestUserModel testUserModel) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            // the mysql insert statement
            String query = " insert into users (first_name, username, password, email,valid)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, testUserModel.getFirstName());
            preparedStmt.setString(2, testUserModel.getUsername());
            preparedStmt.setString(3, testUserModel.getPassword());
            preparedStmt.setString(4, testUserModel.getEmail());
            preparedStmt.setDate(5, startDate);

            // execute the preparedstatement
            preparedStmt.execute();
            preparedStmt.close();
            con.close();

        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }
    }
    public Map<String, String> findTopUsages(String paramToCheckUsage, int rowLimit) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM ( SELECT name,SUM("+paramToCheckUsage+") 'Total' FROM process_map GROUP BY process_id ORDER BY SUM("+paramToCheckUsage+") DESC, process_id ASC) A limit "+Integer.toString(rowLimit);

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                mapOfTopProcesses.put(rs.getString("name"),rs.getString("Total") );
            }
            con.close();
        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }
        return mapOfTopProcesses;
    }
}