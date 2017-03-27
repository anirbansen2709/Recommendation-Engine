package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Anirban on 07-Mar-17.
 */


    @Controller
    public class RatingController {

    private static RatingHandler ratingHandler = RatingHandler.instanceRatingHandler();
    Map<String,String> mapOfSongs = new HashedMap();
    @RequestMapping(value = "getSongsWithAverageRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllUser() {
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel : listOfSongs) {
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

    public static void main(String[] args) {
        RatingController ratingController = new RatingController();
        String s= ratingController.listRatedSong();
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



}
