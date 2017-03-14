package com.gamma.dexter.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Anirban on 17/01/2017.
 */
public class TestDb {



    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/snmp";
    static final String USER = "root";
    static final String PASS = "root";
    private static TestDb instance = null;
    public static TestDb intance()
    {

        if(instance == null) {
            instance = new TestDb();
        }
        return instance;
    }

    public static void main(String[] args) {
        TestDb testDb= TestDb.intance();
        List<Map<String, String>> list=testDb.getData();
    }
    public List<Map<String, String>> getData() {
        List<Map<String, String>> users = new ArrayList<Map<String, String>>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con= DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                Map<String, String> map = new LinkedHashMap<>();

                String id = rs.getString("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String valid = rs.getString("valid");
                String activated = rs.getString("activated");
                String email = rs.getString("email");

                map.put("Id", id);
                map.put("FirstName", first_name);
                map.put("LastName", last_name);
                map.put("UserName", username);
                map.put("Password", password);
                map.put("Valid", valid);
                map.put("Activated", activated);
                map.put("Email", email);
                users.add(map);

            }

            rs.close();
            stmt.close();

        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }

        return users;
    }






}
