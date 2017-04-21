package com.gamma.dexter.musicRecommendation;

import com.gamma.dexter.console.draft.ResponseWrapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.util.Collections;

/**
 * Created by Anirban on 07-Mar-17.
 */


@Controller
public class RatingController {

    public final static String CODE_SUCCESS = "200";
    private static RatingHandler ratingHandler = RatingHandler.instanceRatingHandler();

    @RequestMapping(value = "getSongsWithAverageRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllUser() {
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();

        List<SongsModel> topTenSongs = new ArrayList<SongsModel>(listOfSongs.subList(0, 10));
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
    String listRatedSong() {
        List<RatingModel> listOfRatings = ratingHandler.getHistory();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (RatingModel ratingModel : listOfRatings) {
            wrapper.addPayload(ratingModel);
        }
        return wrapper.getResponse();
    }

    public static void main(String[] args) throws Exception {
        RatingController ratingController = new RatingController();
//        String s= ratingController.getUserDetails("");
        String s = ratingController.getGenresDetails("Action");
//        System.out.println(s);
        int i = 0;
    }

    @RequestMapping(value = "saveRatings", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveRatings(@RequestBody Map<Integer, Integer> mapOfSongs) {
        JSONObject json = new JSONObject();
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            ratingHandler.saveRatings(mapOfSongs);
            json.put("successMessage", "created");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("errorMessage", "Error");
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "getSongsWithGenres", method = RequestMethod.GET)
    public
    @ResponseBody
    String getSongsWithGenres() {
//        Map<String,List<SongsModel>> copy = new HashMap<>();
        Map<String, List<SongsModel>> highestRatedMovies = loadMoviesWithGenres();
//        copy = highestRatedMovies;
        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<SongsModel>> entry : highestRatedMovies.entrySet()) {
            List<SongsModel> all_Songs = entry.getValue();
            List<SongsModel> less_Songs = new ArrayList<>(all_Songs.subList(0, 12));
            entry.setValue(less_Songs);
            wrapper.addPayload(entry);

        }

        return wrapper.getResponse();
    }

    @RequestMapping(value = "getMoreMovies", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoreMovies(@RequestParam int track_page, String genre) {
        ResponseWrapper wrapper = new ResponseWrapper();
        Map<String, List<SongsModel>> highestRatedMovies = loadMoviesWithGenres();
        List<SongsModel> all_Songs = highestRatedMovies.get(genre);
        List<SongsModel> less_Songs = null;
        if((track_page-1)*12<all_Songs.size()) {
            if (all_Songs.size() >= track_page * 12) {
                less_Songs = new ArrayList<>(all_Songs.subList(12 * (track_page - 1), 12 * (track_page)));
            } else {
                less_Songs = new ArrayList<>(all_Songs.subList(12 * (track_page - 1), all_Songs.size()));
            }

            for (SongsModel song : less_Songs) {
                wrapper.addPayload(song);
            }
        }
        else{
            wrapper.setMessage("No More Records");
        }
            return wrapper.getResponse();
    }

    @RequestMapping(value = "getRecommendation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getRecommendation() throws Exception {

        ResponseWrapper wrapper = new ResponseWrapper();
        List<RecommendationModel> listOfRecommendation = ratingHandler.getRecommendation();
        List<RecommendationModel> topTenRecommendation = new ArrayList<>(listOfRecommendation.subList(0, 10));
        for (RecommendationModel recommendationModel : topTenRecommendation) {
            wrapper.addPayload(recommendationModel);
        }
        return wrapper.getResponse();
    }

    //getting recommendation without sublist
    @RequestMapping(value = "getMoviesRecommendation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesRecommendation() throws Exception {
        Map<String, List<RecommendationModel>> SongsWithGenres = loadRecommendationWithGenre();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<RecommendationModel>> entry : SongsWithGenres.entrySet()) {
            List<RecommendationModel> all_Songs = entry.getValue();
            if(all_Songs.size()>12) {
                List<RecommendationModel> less_Songs = new ArrayList<>(all_Songs.subList(0, 12));
                entry.setValue(less_Songs);
            }
            wrapper.addPayload(entry);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "getMoreRecommendation", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoreRecommendation(@RequestParam int track_page, String genre) throws Exception {
        ResponseWrapper wrapper = new ResponseWrapper();
        Map<String, List<RecommendationModel>> highestRatedMovies = loadRecommendationWithGenre();
        List<RecommendationModel> all_Songs = highestRatedMovies.get(genre);
        List<RecommendationModel> less_Songs = null;
        if((track_page-1)*12<all_Songs.size()) {
            if (all_Songs.size() >= track_page * 12) {
                less_Songs = new ArrayList<>(all_Songs.subList(12 * (track_page - 1), 12 * (track_page)));
            } else {
                less_Songs = new ArrayList<>(all_Songs.subList(12 * (track_page - 1), all_Songs.size()));
            }

            for (RecommendationModel song : less_Songs) {
                wrapper.addPayload(song);
            }
        }
        else{
            wrapper.setMessage("No More Records");
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "topRatedSongsChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTopMoviesChart() {
        JSONArray array = new JSONArray();
        Map<String, Float> topRatedSongs = RatingDb.getTopMoviesChart();
        for (Map.Entry<String, Float> entry : topRatedSongs.entrySet()) {
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
        for (Map.Entry<Float, Integer> entry : ratingWithCount.entrySet()) {
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
        Map<String, Integer> countOfGenre = new HashMap<>();
        String genre;
        String[] genres;
//        int count [];
        int value;
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        for (SongsModel song : listOfSongs) {
            genre = song.getGenres();
            genres = genre.split("\\|");
            for (String singleGenre : genres) {
                if (!countOfGenre.containsKey(singleGenre)) {
                    countOfGenre.put(singleGenre, 1);

                } else {
                    value = countOfGenre.get(singleGenre);
                    countOfGenre.put(singleGenre, value + 1);
                }

            }
        }
        JSONArray array = new JSONArray();
//        Map<String, Integer> genreWithCount = countOfGenre.getTopSongsWithGenres();
        for (Map.Entry<String, Integer> entry : countOfGenre.entrySet()) {
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
    String getRecommendationButton() throws Exception {
        //String CODE_SUCCESS = "200";
        JSONObject json = new JSONObject();
        ratingHandler.loadRecommendation();
        json.put("returnCode", "200");
        json.put("message", "");
        json.put("Payload", "");

        return json.toString();

    }


    @RequestMapping(value = "userDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getUserDetails(@RequestParam String movieName) throws Exception {
        List<RatingModel> listOfAllSongs = ratingHandler.getUserDetails(movieName);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (RatingModel ratingModel : listOfAllSongs) {
            wrapper.addPayload(ratingModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "moviesDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesDetails(@RequestParam Integer movieRating) throws Exception {
        List<SongsModel> listOfAllMovies = ratingHandler.getMoviesDetails(movieRating);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel : listOfAllMovies) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "genresDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getGenresDetails(@RequestParam String genre) throws Exception {
        List<SongsModel> listOfAllMoviesWithGenre = ratingHandler.getGenresDetails(genre);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (SongsModel songsModel : listOfAllMoviesWithGenre) {
            wrapper.addPayload(songsModel);
        }
        return wrapper.getResponse();

    }

    public Map<String, List<SongsModel>> loadMoviesWithGenres() {
        Map<String, List<SongsModel>> highestRatedMovies = new HashMap<>();
        String genre;
        String[] genres;
        List<SongsModel> listOfSongs = ratingHandler.getSongsWithAverageRatings();
        for (SongsModel song : listOfSongs) {
            genre = song.getGenres();
            genres = genre.split("\\|");
            for (String singleGenre : genres) {
                if (!highestRatedMovies.containsKey(singleGenre)) {
                    List<SongsModel> listOfSongsWithGenre = new ArrayList<>();
                    listOfSongsWithGenre.add(song);
                    highestRatedMovies.put(singleGenre, listOfSongsWithGenre);
                } else {
                    List<SongsModel> listOfSongsWithGenre = highestRatedMovies.get(singleGenre);
                    listOfSongsWithGenre.add(song);
                }
            }
        }
        return highestRatedMovies;
    }
    public Map<String, List<RecommendationModel>> loadRecommendationWithGenre() throws Exception {
        Map<String, List<RecommendationModel>> highestRatedMovies = new HashMap<>();
        String genre;
        String[] genres;
        List<RecommendationModel> listOfSongs = ratingHandler.getRecommendation();
        for (RecommendationModel song : listOfSongs) {
            genre = song.getGenres();
            genres = genre.split("\\|");
            for (String singleGenre : genres) {
                if (!highestRatedMovies.containsKey(singleGenre)) {
                    List<RecommendationModel> listOfSongsWithGenre = new ArrayList<>();
                    listOfSongsWithGenre.add(song);
                    highestRatedMovies.put(singleGenre, listOfSongsWithGenre);
                } else {
                    List<RecommendationModel> listOfSongsWithGenre = highestRatedMovies.get(singleGenre);
                    listOfSongsWithGenre.add(song);
                }
            }
        }
        return highestRatedMovies;
    }
}

