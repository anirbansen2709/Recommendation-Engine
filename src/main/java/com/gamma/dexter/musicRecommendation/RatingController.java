package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    public static void main(String[] args) {
        RatingController ratingController = new RatingController();
        String hello = ratingController.listAllUser();
        int i = 0;
    }

    @RequestMapping(value = "saveRatings", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveRatings(@RequestBody Map<String,String> mapOfSongs) {
        ResponseWrapper wrapper = new ResponseWrapper();
//        try {
//            if (!testHandler.insertUser(testUserModel)) {
//                wrapper.setSuccess(false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return wrapper.getResponse();
    }
}