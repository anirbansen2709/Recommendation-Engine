package com.gamma.dexter.musicRecommendation;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static RatingDb getInstance() {

        if (instance == null) {
            instance = new RatingDb();
        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        RatingDb ratingDb = RatingDb.getInstance();
        List<RecommendationModel> list = ratingDb.getRecommendation();
        System.out.println(list);
        int i = 0;
    }

    public void saveRatings(Map<Integer, Integer> mapOfSongs) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            //check for existing movieID and UserID combination and remove the same
            String query1 = "DELETE from ratings where userId=? and movieId=?";
            PreparedStatement stmt1 = con.prepareStatement(query1);
            for (Map.Entry<Integer, Integer> entry : mapOfSongs.entrySet()) {
                stmt1.setInt(1, 0);
                stmt1.setInt(2, entry.getKey());
                stmt1.addBatch();
            }
            stmt1.executeBatch();
            stmt1.close();
            con.close();

            //insert values when no previous of movieID and UserID combination exist
            Connection con1 = DriverManager.getConnection(DB_URL, USER, PASS);
            String query = "insert into ratings values (?, ?, ?, ?)";
            PreparedStatement stmt = con1.prepareStatement(query);
            for (Map.Entry<Integer, Integer> entry : mapOfSongs.entrySet()) {
                stmt.setInt(1, 0);
                stmt.setInt(2, entry.getKey());
                stmt.setFloat(3, entry.getValue());
                stmt.setString(4, String.valueOf(timestamp.getTime()));
                stmt.addBatch();
            }
            stmt.executeBatch();
            stmt.close();
            con1.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    public List<SongsModel> getSongsWithAverageRatingsFromMemory() {
        if (songsDetails == null) {
            songsDetails = getSongsWithAverageRatings();
        }
        return songsDetails;
    }

    public List<SongsModel> getSongsWithAverageRatings() {
        List<SongsModel> listOfSongs = new ArrayList<SongsModel>();
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
                songsModel.setAvgRating((int) rs.getFloat("averageRatings"));
                songsModel.setMovieId(rs.getInt("movieId"));
                songsModel.setGenres(rs.getString("genres").replace("\r", ""));
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

    public List<RatingModel> getHistory() {
        String time;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<RatingModel> listOfRatings = new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = " select r.userId, m.movieId, m.title,m.genres, r.rating, r.timestamp from ratings r,movies m where m.movieId= r.movieId and r.userId = 0;";
            ResultSet resultSet = stmt.executeQuery(sql);
            long time1;
            while (resultSet.next()) {

                RatingModel ratingModel = new RatingModel();
                ratingModel.setUserId(resultSet.getInt("userId"));
                ratingModel.setMovieId(resultSet.getInt("movieId"));
                ratingModel.setName(resultSet.getString("title"));
                ratingModel.setGenres(resultSet.getString("genres"));
                ratingModel.setRating((int) resultSet.getFloat("rating"));
                time = resultSet.getString("timestamp").replace("\r", "");
                time1 = Long.parseLong(time);
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
//access recommendation from server
    public List<RecommendationModel> getRecommendation() throws Exception {
        List<RecommendationModel> listOfRecommendation = new ArrayList<>();
        Class.forName(JDBC_DRIVER);
        Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        String query = "select * from recommendation where userId=0";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {

                RecommendationModel recommendationModel = new RecommendationModel();
//                recommendationModel.setCount(rs.getInt("count"));
                recommendationModel.setGenres(rs.getString("genres"));
                recommendationModel.setMovieId(rs.getInt("movieId"));
                recommendationModel.setTitle(rs.getString("title"));
                recommendationModel.setAverage(rs.getFloat("average"));
                recommendationModel.setUserId(rs.getInt("userId"));
//                recommendationModel.setPrediction(rs.getFloat("prediction"));
                listOfRecommendation.add(recommendationModel);
            }
            return listOfRecommendation;
    }
//python server
    public void loadRecommendation() {
        try {
            HttpUtil httpUtil = new HttpUtil();
            JSONObject recommendation = httpUtil.getRecommendation();
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            con.setAutoCommit(false);
            int result = stmt.executeUpdate("DELETE FROM recommendation WHERE userId = 0");
            System.out.println("rows Affected:" + result);
            JSONObject objectInArray;

            JSONArray jsonArray = recommendation.getJSONArray("Payload");
            String insertquery = "insert into recommendation(genres,movieId,title,userId,average,rank) values (?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(insertquery);
            for (int i = 0; i < jsonArray.size(); i++) {
                int rank = i + 1;
                objectInArray = jsonArray.getJSONObject(i);
                preparedStatement.setString(1, objectInArray.getString("genres"));
                preparedStatement.setInt(2, objectInArray.getInt("movieId"));
                preparedStatement.setString(3, objectInArray.getString("title"));
                preparedStatement.setInt(4, objectInArray.getInt("userId"));
                preparedStatement.setFloat(5, objectInArray.getInt("average"));
                preparedStatement.setInt(6, rank);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            stmt.close();
            con.commit();
            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static Map<String, Float> getTopMoviesChart() {
        Map<String, Float> topRatedSongs = new HashMap<>();
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
                topRatedSongs.put(rs.getString("name"), rs.getFloat("averageRatings"));
            }
            con.close();
        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }
        return topRatedSongs;
    }

    public static Map<Float, Integer> getRatingWithCount() {
        Map<Float, Integer> ratingWithCount = new HashMap<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String query = "\n" +
                    "select CEILING(r.rating) as Rating, count(*) as noOfMovies from ratings r \n" +
                    "where r.rating=' \" ' + rating + ' \" ' group by CEILING(r.rating) order by r.rating DESC;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
//            int i= rs.getInt("averageRatings");
//                String s= rs.getString("name");
                ratingWithCount.put(rs.getFloat("Rating"), rs.getInt("noOfMovies"));
            }
            con.close();
        } catch (Exception e) {

            System.out.println("createStatementError in getUsers()" + e);
        }
        return ratingWithCount;
    }
//    public List<RatingModel> getUserDetails(String movieName) {
//        String time;
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        List<RatingModel> listOfUsers= new ArrayList<>();
//        try {
//            Class.forName(JDBC_DRIVER);
//            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
//            Statement stmt = con.createStatement();
//
//            String sql = "select r.userId as UserId, r.rating as Rating, r.timestamp as Time\n" +
//                    "from ratings r inner join movies m where m.title = \""+movieName+" \"and m.movieId = r.movieId;";
//            ResultSet resultSet = stmt.executeQuery(sql);
//            long time1;
//            while (resultSet.next()) {
//
//                RatingModel ratingModel = new RatingModel();
//                ratingModel.setUserId(resultSet.getInt("UserId"));
//                ratingModel.setRating((int) resultSet.getFloat("Rating"));
//                time = resultSet.getString("Time").replace("\r", "");
//                time1 = Long.parseLong(time);
//                timestamp.setTime(time1);
//                System.out.println(timestamp);
//                ratingModel.setTimestamp(sdf.format(timestamp));
//                listOfUsers.add(ratingModel);
//            }
//            resultSet.close();
//            stmt.close();
//            con.close();
//        } catch (Exception e) {
//            System.out.println("createStatementError in getUsers()" + e);
//        }
//        return listOfUsers;
//
//    }

//*************Second Chart******************//
    public List<SongsModel> getMoviesDetails(Integer movieRating) {

        List<SongsModel> listOfMovies= new ArrayList<>();
        try {
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();

            String sql = "\n" +
                    "select m.movieId, m.title,count(m.title) as noOfUsers, m.genres from ratings r, movies m " +
                    "where m.movieId = r.movieId and CEILING(r.rating) = \""+movieRating+" \" group by m.movieId;";
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {

                SongsModel songsModel = new SongsModel();
                songsModel.setMovieId(resultSet.getInt("movieId"));
                songsModel.setName(resultSet.getString("title"));
                songsModel.setNoOfUsers(resultSet.getInt("noOfUsers"));
                songsModel.setGenres(resultSet.getString("genres"));

                listOfMovies.add(songsModel);
            }
            resultSet.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("createStatementError in getUsers()" + e);
        }
        return listOfMovies;

    }

}