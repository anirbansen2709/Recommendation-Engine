package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//import java.util.Collections;

/**
 * Created by Anirban on 07-Mar-17.
 */


    @Controller
    public class RatingController {
    public final static String CODE_SUCCESS = "200";
    private static RatingHandler ratingHandler = RatingHandler.instanceRatingHandler();
    Map<String,String> mapOfSongs = new HashedMap();
    @RequestMapping(value = "getSongsWithAverageRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllUser() {
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();

        List<SongsModel> topTenSongs = new ArrayList<SongsModel>(listOfSongs.subList(0,10));
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel : topTenSongs) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "getAllSongsWithRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllTopSongs() {
        List<SongsModel> listOfAllSongs = ratingHandler.getSongsWithAverageRatings();

        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel : listOfAllSongs) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }




    @RequestMapping(value = "listRatedSongs", method = RequestMethod.GET)
    public
    @ResponseBody
    String listRatedSong(){
        List<RatingModel> listOfRatings = ratingHandler.getHistory();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (RatingModel ratingModel : listOfRatings) {
            wrapper.addPayload(ratingModel);
        }
        return wrapper.getResponse();
    }

    public static void main(String[] args) throws Exception{
        RatingController ratingController = new RatingController();
//        String s= ratingController.getUserDetails("");
        String s= ratingController.getGenresDetails("Action");
//        System.out.println(s);
        int i = 0;
    }

    @RequestMapping(value = "saveRatings", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveRatings(@RequestBody Map<Integer,Integer> mapOfSongs) {
        JSONObject json = new JSONObject();
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            ratingHandler.saveRatings(mapOfSongs);
            json.put("successMessage", "created");
            }
         catch (Exception e) {
            e.printStackTrace();
             json.put("errorMessage", "Error");
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "getSongsWithGenres", method = RequestMethod.GET)
    public
    @ResponseBody
    String getSongsWithGenres() {
        Map<String,List<SongsModel>> SongsWithGenres = new HashedMap();
        String genre;
        String[] genres;
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        for(SongsModel song :listOfSongs){
           genre = song.getGenres();
           genres= genre.split("\\|");
            for(String singleGenre:genres)
            {
                if(!SongsWithGenres.containsKey(singleGenre)) {
                    List<SongsModel> listOfSongsWithGenre = new ArrayList<>();
                    listOfSongsWithGenre.add(song);
                        SongsWithGenres.put(singleGenre,listOfSongsWithGenre);
                }
                else{
                    List<SongsModel> listOfSongsWithGenre = SongsWithGenres.get(singleGenre);
                    listOfSongsWithGenre.add(song);
                }
            }
        }

        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<SongsModel>> entry : SongsWithGenres.entrySet())
        {
            wrapper.addPayload(entry);
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "getRecommendation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getRecommendation() throws Exception{

        ResponseWrapper wrapper = new ResponseWrapper();
        List<RecommendationModel> listOfRecommendation =ratingHandler.getRecommendation();
        List<RecommendationModel> topTenRecommendation = new ArrayList<>(listOfRecommendation.subList(0,10));
        for (RecommendationModel recommendationModel : topTenRecommendation) {
            wrapper.addPayload(recommendationModel);
        }
        return wrapper.getResponse();
    }
    //getting recommendation without sublist
    @RequestMapping(value = "getMoviesRecommendation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesRecommendation() throws Exception{
        Map<String,List<RecommendationModel>> SongsWithGenres = new HashedMap();
        String genre;
        String[] genres;
        List<RecommendationModel> listOfRecommendation =ratingHandler.getRecommendation();
        for(RecommendationModel song :listOfRecommendation){
            genre = song.getGenres();
            genres= genre.split("\\|");
            for(String singleGenre:genres)
            {
                if(!SongsWithGenres.containsKey(singleGenre)) {
                    List<RecommendationModel> listOfSongsWithGenre = new ArrayList<>();
                    listOfSongsWithGenre.add(song);
                    SongsWithGenres.put(singleGenre,listOfSongsWithGenre);
                }
                else{
                    List<RecommendationModel> listOfSongsWithGenre = SongsWithGenres.get(singleGenre);
                    listOfSongsWithGenre.add(song);
                }
            }
        }
        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<RecommendationModel>> entry : SongsWithGenres.entrySet())
        {
            wrapper.addPayload(entry);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "topRatedSongsChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTopMoviesChart() {
        JSONArray array = new JSONArray();
        Map<String, Float> topRatedSongs = RatingDb.getTopMoviesChart();
        for (Map.Entry<String, Float> entry : topRatedSongs.entrySet())
        {
            entry.getKey();
            entry.getValue();
            JSONObject json = new JSONObject();
            json.put("label", entry.getKey());
            json.put("value", entry.getValue());
            array.add(json);

        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj.toString();

    }

    @RequestMapping(value = "ratingsWithCountChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getRatingWithCount() {
        JSONArray array = new JSONArray();
        Map<Float, Integer> ratingWithCount = RatingDb.getRatingWithCount();
        for (Map.Entry<Float,Integer> entry : ratingWithCount.entrySet())
        {
            entry.getKey();
            entry.getValue();
            JSONObject json = new JSONObject();
            json.put("label", entry.getKey());
            json.put("value", entry.getValue());
            array.add(json);

        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj.toString();

    }

    @RequestMapping(value = "getTopGenresWithCountChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTopSongsWithGenres() {
//        Map<String,List<SongsModel>> SongsWithGenres = new HashedMap();
        Map<String, Integer> countOfGenre= new HashMap<>();
        String genre;
        String[] genres;
//        int count [];
        int value;
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        for(SongsModel song :listOfSongs){
            genre = song.getGenres();
            genres= genre.split("\\|");
            for(String singleGenre:genres)
            {
                if(!countOfGenre.containsKey(singleGenre)) {
                    countOfGenre.put(singleGenre,1);

                }
                else{
                    value = countOfGenre.get(singleGenre);
                    countOfGenre.put(singleGenre,value+1);
                }

            }
        }
        JSONArray array = new JSONArray();
//        Map<String, Integer> genreWithCount = countOfGenre.getTopSongsWithGenres();
        for (Map.Entry<String,Integer> entry : countOfGenre.entrySet())
        {
            entry.getKey();
            entry.getValue();
            JSONObject json = new JSONObject();
            json.put("label", entry.getKey());
            json.put("value", entry.getValue());
            array.add(json);

        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("data", array);

        return mainObj.toString();
    }

    @RequestMapping(value = "getRecommendationButton", method = RequestMethod.GET)
    public
    @ResponseBody
    String getRecommendationButton()throws Exception{
        ratingHandler.loadRecommendation();
    return null;
    }


    @RequestMapping(value = "userDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getUserDetails(@RequestParam String movieName)throws Exception{
        List<RatingModel> listOfAllSongs = ratingHandler.getUserDetails(movieName);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (RatingModel ratingModel: listOfAllSongs) {
            wrapper.addPayload(ratingModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "moviesDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesDetails(@RequestParam Integer movieRating)throws Exception{
        List<SongsModel> listOfAllMovies = ratingHandler.getMoviesDetails(movieRating);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel: listOfAllMovies) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "genresDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getGenresDetails(@RequestParam String genre)throws Exception{
        List<SongsModel> listOfAllMoviesWithGenre = ratingHandler.getGenresDetails(genre);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel: listOfAllMoviesWithGenre) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }

}

