package com.gamma.dexter.musicRecommendation;

import java.util.List;

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
}