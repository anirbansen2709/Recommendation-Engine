package com.gamma.dexter.musicRecommendation;

/**
 * Created by Anirban on 08-Mar-17.
 */
public class SongsModel {

    String name;
    float avgRating;
    Integer movieId;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
