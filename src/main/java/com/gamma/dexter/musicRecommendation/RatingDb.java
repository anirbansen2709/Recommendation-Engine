package com.gamma.dexter.musicRecommendation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Anirban on 08-Mar-17.
 */

public class RatingDb {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/music";
    static final String USER = "root";
    static final String PASS = "root";
    private static RatingDb instance = null;
    private static List<SongsModel> songsDetails;


    public static RatingDb intance() {

        if (instance == null) {
            instance = new RatingDb();
        }
        return instance;
    }
    public static void main(String[] args) {
        RatingDb ratingDb = new RatingDb();
        List<RatingModel> list= ratingDb.getHistory();
        System.out.println(list);
        int i=0;

    }
    public void saveRatings(Map<Integer,Integer> mapOfSongs) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "insert into ratings values (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            for (Map.Entry<Integer, Integer> entry : mapOfSongs.entrySet()) {
                    stmt.setInt(1, 0);
                    stmt.setInt(2, entry.getKey());
                    stmt.setFloat(3, entry.getValue());
                    stmt.setString(4, String.valueOf(timestamp.getTime()));
                    stmt.addBatch();
                }
            stmt.executeBatch();
            stmt.close();
            con.close();
        }
        catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public List<SongsModel> getSongsWithAverageRatingsFromMemory(){
        if(songsDetails == null){
           songsDetails = getSongsWithAverageRatings();
        }
            return songsDetails;
    }

    public List<SongsModel> getSongsWithAverageRatings(){
        List<SongsModel> listOfSongs= new ArrayList<SongsModel>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = " select count(r.userId) as noOfUsers,\n" +
                    "     avg(r.rating) as averageRatings,\n" +
                    "     r.movieId , m.title, m.genres from ratings r inner join movies m\n" +
                    "    where m.movieId = r.movieId \n" +
                    "     group by r.movieId\n" +
                    "     having count(r.userId)>200\n" +
                    "     order by averageRatings desc;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {

                SongsModel songsModel = new SongsModel();
                songsModel.setNoOfUsers(rs.getInt("noOfUsers"));
                songsModel.setName(rs.getString("title"));
                songsModel.setAvgRating((int)rs.getFloat("averageRatings"));
                songsModel.setMovieId(rs.getInt("movieId"));
                songsModel.setGenres(rs.getString("genres").replace("\r",""));
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
    public List<RatingModel> getHistory(){
      String time;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<RatingModel> listOfRatings= new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = " select r.userId, m.movieId, m.title,m.genres, r.rating, r.timestamp from ratings r,movies m where m.movieId= r.movieId and r.userId = 1;";
            ResultSet resultSet = stmt.executeQuery(sql);
            long time1;
            while( resultSet.next()) {

               RatingModel ratingModel = new RatingModel();
               ratingModel.setUserId(resultSet.getInt("userId"));
               ratingModel.setMovieId(resultSet.getInt("movieId"));
               ratingModel.setName(resultSet.getString("title"));
                ratingModel.setGenres(resultSet.getString("genres"));
               ratingModel.setRating((int)resultSet.getFloat("rating"));
                time = resultSet.getString("timestamp").replace("\r","");
                 time1=Long.parseLong(time);
                timestamp.setTime(time1);
                System.out.println(timestamp);
                ratingModel.setTimestamp(sdf.format(timestamp));
                listOfRatings.add(ratingModel);
            }
            resultSet.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("createStatementError in getUsers()" + e);
        }
        return listOfRatings;

    }

    public static Map<String,Float> getTopMoviesChart(){
        Map<String,Float> topRatedSongs = new HashMap<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String query = "select m.title as name, avg(r.rating) as averageRatings from ratings r \n" +
                    " inner join movies m where m.title = ' \" ' + title + ' \" ' and m.movieId = r.movieId \n" +
                    " group by r.movieId  having count(r.userId)>200 order by averageRatings DESC limit 5;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
//            int i= rs.getInt("averageRatings");
//                String s= rs.getString("name");
                topRatedSongs.put(rs.getString("name"),rs.getFloat("averageRatings") );
            }
            con.close();
        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }
        return topRatedSongs;
    }

}