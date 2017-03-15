package com.gamma.dexter.musicRecommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        int i=0;
    }
    public void saveRatings(Map<Integer,Integer> mapOfSongs) {
        try {
//            Class.forName(JDBC_DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            for (Map.Entry<String, String> entry : mapOfSongs.entrySet())
//            {     String movieId =  entry.getKey();
//                String query = "insert into ratings vales (?,?,?,?)";
//                PreparedStatement preparedStatement = con.prepareStatement(query);
//                preparedStatement.setInt(1, 0);
//                preparedStatement.setInt(2, entry.getKey());
//                preparedStatement.setInt(3, entry.getValue());
//                preparedStatement.setString(4, "xyz");
//                preparedStatement.execute();
//                preparedStatement.close();
//            }
//
//            con.close();
            int i=0;
        }
        catch (Exception e) {
            System.out.println("" + e);
        }
    }
    public List<SongsModel> getSongsWithAverageRatings(){
        List<SongsModel> listOfSongs= new ArrayList<SongsModel>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "SELECT title,avgRatings,movieId FROM movies where movieId < 50";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SongsModel songsModel = new SongsModel();
                songsModel.setName(rs.getString("title"));
                songsModel.setAvgRating((int)rs.getFloat("avgRatings"));
                songsModel.setMovieId(rs.getInt("movieId"));
                listOfSongs.add(songsModel);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("createStatementError in getUsers()" + e);
        }
        return listOfSongs;

    }
}
