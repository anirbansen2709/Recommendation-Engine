package com.gamma.dexter.musicRecommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anirban on 08-Mar-17.
 */

public class RatingDb {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/music";
    static final String USER = "root";
    static final String PASS = "root";
    private static RatingDb instance = null;
    public static RatingDb intance() {

        if (instance == null) {
            instance = new RatingDb();
        }
        return instance;
    }
    public static void main(String[] args) {
        RatingDb ratingDb = RatingDb.intance();
        ratingDb.saveRatings();
        List<SongsModel> listOfSongs = ratingDb.getSongsWithAverageRatings();
        int i=0;
    }
    public void saveRatings() {

    }
    public List<SongsModel> getSongsWithAverageRatings(){
        List<SongsModel> listOfSongs= new ArrayList<SongsModel>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "SELECT title,avgRatings FROM movies where movieId < 50";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SongsModel songsModel = new SongsModel();
                songsModel.setName(rs.getString("title"));
                songsModel.setAvgRating((int)rs.getFloat("avgRatings"));

                listOfSongs.add(songsModel);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("createStatementError in getUsers()" + e);
        }
        return listOfSongs;

    }
}
