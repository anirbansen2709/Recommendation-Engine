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
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        int i=0;
    }
    public List<SongsModel> getSongsWithAverageRatings() {
        return ratingServices.getSongsWithAverageRatings();

    }

    public  void saveRatings(Map<Integer,Integer> mapOfSongs)throws Exception{
        ratingServices.saveRatings(mapOfSongs);
    }
    public List<RatingModel> getHistory(){
        return ratingServices.getHistory();
    }

    public List<RecommendationModel> getRecommendation() throws Exception{ return ratingServices.getRecommendation();}

    public void loadRecommendation()throws Exception
    {
        ratingServices.loadRecommendation();
    }
}