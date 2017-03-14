package com.gamma.dexter.musicRecommendation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class RatingDb {
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
        SongsModel song1= new SongsModel("Yellow",5);
        SongsModel song2= new SongsModel("Everglow",4);
        SongsModel song3 = new SongsModel("XYZ",3);
        List<SongsModel> listOfSongs= new ArrayList<SongsModel>();
        listOfSongs.add(song1);
        listOfSongs.add(song2);
        listOfSongs.add(song3);
        return  listOfSongs;
    }
}
