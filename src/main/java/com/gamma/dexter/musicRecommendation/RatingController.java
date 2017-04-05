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

        HttpUtil httpUtil=new HttpUtil();
        ResponseWrapper responseWrapper = new ResponseWrapper();
        JSONObject str=httpUtil.getRecommendation();
        responseWrapper.addPayload(str.get("Payload"));
        str.put("returnCode", CODE_SUCCESS);
        str.put("message", "");

        return responseWrapper.getResponse();

    }

}
