package com.gamma.dexter.musicRecommendation;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class RatingServices {
    RatingDb ratingDb = RatingDb.getInstance();
    private static RatingServices instance = null;
    private static UserContext userContext = UserContext.getInstance();

    public static RatingServices instanceRatingServices() {
        if (instance == null) {
            instance = new RatingServices();
        }
        return instance;
    }
    public static void main(String[] args) {
        RatingServices ratingServices = RatingServices.instanceRatingServices();
        List<MoviesModel> listOfMovies = ratingServices.getMoviesWithAverageRatings();
        int i=0;
    }
    public List<MoviesModel> getMoviesWithAverageRatings() {
        return ratingDb.getMoviesWithAverageRatingsFromMemory();
    }

    public  void saveRatings(Map<Integer,Integer> mapOfMovies) throws Exception{
        int userId= userContext.getUserId();
        ratingDb.saveRatings(mapOfMovies,userId);
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.sendRatings(mapOfMovies,userId);
    }
    public List<RatingModel> getHistory(){
        int userId= userContext.getUserId();
        return ratingDb.getHistory(userId);
    }


    public List<RecommendationModel> getRecommendation ()throws Exception{
        int userId= userContext.getUserId();
        return ratingDb.getRecommendation(userId);}

    public void loadRecommendation() throws Exception
    {   int userId= userContext.getUserId();
        ratingDb.loadRecommendation(userId);
    }
    public List<RatingModel> getUserDetails(String movieName)throws Exception
    {
        return ratingDb.getUserDetails(movieName);
    }

    public List<MoviesModel> getMoviesDetails(Integer movieRating)throws Exception
    {
        return ratingDb.getMoviesDetails(movieRating);
    }
      public List<MoviesModel> getGenresDetails(String genre)throws Exception
      {
          return ratingDb.getGenresDetails(genre);
      }

}
