package com.gamma.dexter.musicRecommendation;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class RatingHandler {
    RatingServices ratingServices = RatingServices.instanceRatingServices();
    private static RatingHandler instance = null;

    public static RatingHandler instanceRatingHandler() {
        if (instance == null) {
            instance = new RatingHandler();
        }
        return instance;
    }
    public static void main(String[] args) {
        RatingHandler ratingHandler = RatingHandler.instanceRatingHandler();
        List<MoviesModel> listOfMovies = ratingHandler.getMoviesWithAverageRatings();
        int i=0;
    }
    public List<MoviesModel> getMoviesWithAverageRatings() {
        return ratingServices.getMoviesWithAverageRatings();

    }

    public  void saveRatings(Map<Integer,Integer> mapOfMovies)throws Exception{
        ratingServices.saveRatings(mapOfMovies);
    }
    public List<RatingModel> getHistory(){
        return ratingServices.getHistory();
    }

    public List<RecommendationModel> getRecommendation() throws Exception{ return ratingServices.getRecommendation();}

    public void loadRecommendation()throws Exception
    {
        ratingServices.loadRecommendation();
    }
    public List<RatingModel> getUserDetails(String movieName)throws Exception
    {
        return ratingServices.getUserDetails(movieName);
    }
    public List<MoviesModel> getMoviesDetails(Integer movieRating)throws Exception {

        return ratingServices.getMoviesDetails(movieRating);
    }
      public List<MoviesModel> getGenresDetails(String genre)throws Exception {

         return ratingServices.getGenresDetails(genre);
      }

}