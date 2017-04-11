package com.gamma.dexter.musicRecommendation;

import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class RatingServices {
    RatingDb ratingDb = RatingDb.getInstance();
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
        return ratingDb.getSongsWithAverageRatingsFromMemory();
    }

    public  void saveRatings(Map<Integer,Integer> mapOfSongs) throws Exception{
        ratingDb.saveRatings(mapOfSongs);
        HttpUtil httpUtil = new HttpUtil();
        httpUtil.sendRatings(mapOfSongs);
    }
    public List<RatingModel> getHistory(){
        return ratingDb.getHistory();
    }


    public List<RecommendationModel> getRecommendation ()throws Exception{ return ratingDb.getRecommendation();}
}
