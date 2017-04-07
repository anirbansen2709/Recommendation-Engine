package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Map;

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

//        List<SongsModel> topTenSongs = new ArrayList<SongsModel>(listOfSongs.subList(0,10));
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
        String s= ratingController.listAllUser();
        System.out.println(s);
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

        ResponseWrapper responseWrapper = new ResponseWrapper();
        List<SongsModel> str=ratingHandler.getRecommendation();
//        responseWrapper.addPayload(str.get("Payload"));
//        str.put("returnCode", CODE_SUCCESS);
//        str.put("message", "");

        return responseWrapper.getResponse();

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


    @RequestMapping(value = "userDetailsChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getUserDetails() {
        JSONArray array = new JSONArray();
        Map<Float, Integer> userDetails = RatingDb.getRatingWithCount();
        for (Map.Entry<Float,Integer> entry : userDetails.entrySet())
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

}
