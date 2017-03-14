package com.gamma.dexter.musicRecommendation;

import java.util.List;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class RatingServices {
    RatingDb ratingDb = RatingDb.intance();
    private static RatingServices instance = null;

    public static RatingServices instanceRatingServices() {
        if (instance == null) {
            instance = new RatingServices();
        }
        return instance;
    }
    public static void main(String[] args) {
        RatingServices ratingServices = RatingServices.instanceRatingServices();
        List<SongsModel> listOfSongs = ratingServices.getSongsWithAverageRatings();
        int i=0;
    }
    public List<SongsModel> getSongsWithAverageRatings() {
        return ratingDb.getSongsWithAverageRatings();
    }
}
