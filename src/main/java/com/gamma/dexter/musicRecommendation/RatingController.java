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

    @RequestMapping(value = "getMoviesWithAverageRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllUser() {
        List<MoviesModel> listOfMovies = ratingHandler.getMoviesWithAverageRatings();

        List<MoviesModel> topTenMovies = new ArrayList<MoviesModel>(listOfMovies.subList(0, 10));
        ResponseWrapper wrapper = new ResponseWrapper();
        for (MoviesModel moviesModel : topTenMovies) {
            wrapper.addPayload(moviesModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "getAllMoviesWithRatings", method = RequestMethod.GET)
    public
    @ResponseBody
    String listAllTopMovies() {
        List<MoviesModel> listOfAllMovies = ratingHandler.getMoviesWithAverageRatings();

        ResponseWrapper wrapper = new ResponseWrapper();
        for (MoviesModel moviesModel : listOfAllMovies) {
            wrapper.addPayload(moviesModel);
        }
        return wrapper.getResponse();

    }


    @RequestMapping(value = "listRatedMovies", method = RequestMethod.GET)
    public
    @ResponseBody
    String listRatedMovies() {
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
    String saveRatings(@RequestBody Map<Integer, Integer> mapOfMovies) {
        JSONObject json = new JSONObject();
        ResponseWrapper wrapper = new ResponseWrapper();
        try {
            ratingHandler.saveRatings(mapOfMovies);
            json.put("successMessage", "created");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("errorMessage", "Error");
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "getMoviesWithGenres", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesWithGenres() {
//        Map<String,List<MoviesModel>> copy = new HashMap<>();
        Map<String, List<MoviesModel>> highestRatedMovies = loadMoviesWithGenres();
//        copy = highestRatedMovies;
        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<MoviesModel>> entry : highestRatedMovies.entrySet()) {
            List<MoviesModel> all_Movies = entry.getValue();
            List<MoviesModel> less_Movies = new ArrayList<>(all_Movies.subList(0, 12));
            entry.setValue(less_Movies);
            wrapper.addPayload(entry);

        }

        return wrapper.getResponse();
    }

    @RequestMapping(value = "getMoreMovies", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoreMovies(@RequestParam int track_page, String genre) {
        ResponseWrapper wrapper = new ResponseWrapper();
        Map<String, List<MoviesModel>> highestRatedMovies = loadMoviesWithGenres();
        List<MoviesModel> all_Movies = highestRatedMovies.get(genre);
        List<MoviesModel> less_Movies = null;
        if((track_page-1)*12<all_Movies.size()) {
            if (all_Movies.size() >= track_page * 12) {
                less_Movies = new ArrayList<>(all_Movies.subList(12 * (track_page - 1), 12 * (track_page)));
            } else {
                less_Movies = new ArrayList<>(all_Movies.subList(12 * (track_page - 1), all_Movies.size()));
            }

            for (MoviesModel movie : less_Movies) {
                wrapper.addPayload(movie);
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
        Map<String, List<RecommendationModel>> MoviesWithGenres = loadRecommendationWithGenre();
        ResponseWrapper wrapper = new ResponseWrapper();
        for (Map.Entry<String, List<RecommendationModel>> entry : MoviesWithGenres.entrySet()) {
            List<RecommendationModel> all_Movies = entry.getValue();
            if(all_Movies.size()>12) {
                List<RecommendationModel> less_Movies = new ArrayList<>(all_Movies.subList(0, 12));
                entry.setValue(less_Movies);
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
        List<RecommendationModel> all_Movies = highestRatedMovies.get(genre);
        List<RecommendationModel> less_Movies = null;
        if((track_page-1)*12<all_Movies.size()) {
            if (all_Movies.size() >= track_page * 12) {
                less_Movies = new ArrayList<>(all_Movies.subList(12 * (track_page - 1), 12 * (track_page)));
            } else {
                less_Movies = new ArrayList<>(all_Movies.subList(12 * (track_page - 1), all_Movies.size()));
            }

            for (RecommendationModel movie : less_Movies) {
                wrapper.addPayload(movie);
            }
        }
        else{
            wrapper.setMessage("No More Records");
        }
        return wrapper.getResponse();
    }

    @RequestMapping(value = "topRatedMoviesChart", method = RequestMethod.GET)
    public
    @ResponseBody
    String getTopMoviesChart() {
        JSONArray array = new JSONArray();
        Map<String, Float> topRatedMovies = RatingDb.getTopMoviesChart();
        for (Map.Entry<String, Float> entry : topRatedMovies.entrySet()) {
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
    String getTopMoviesWithGenres() {
        Map<String, Integer> countOfGenre = new HashMap<>();
        String genre;
        String[] genres;
//        int count [];
        int value;
        List<MoviesModel> listOfMovies = ratingHandler.getMoviesWithAverageRatings();
        for (MoviesModel movie : listOfMovies) {
            genre = movie.getGenres();
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
        List<RatingModel> listOfAllMovies = ratingHandler.getUserDetails(movieName);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (RatingModel ratingModel : listOfAllMovies) {
            wrapper.addPayload(ratingModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "moviesDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getMoviesDetails(@RequestParam Integer movieRating) throws Exception {
        List<MoviesModel> listOfAllMovies = ratingHandler.getMoviesDetails(movieRating);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (MoviesModel moviesModel : listOfAllMovies) {
            wrapper.addPayload(moviesModel);
        }
        return wrapper.getResponse();

    }

    @RequestMapping(value = "genresDetails", method = RequestMethod.GET)
    public
    @ResponseBody
    String getGenresDetails(@RequestParam String genre) throws Exception {
        List<MoviesModel> listOfAllMoviesWithGenre = ratingHandler.getGenresDetails(genre);
        ResponseWrapper wrapper = new ResponseWrapper();
        for (MoviesModel moviesModel : listOfAllMoviesWithGenre) {
            wrapper.addPayload(moviesModel);
        }
        return wrapper.getResponse();

    }

    public Map<String, List<MoviesModel>> loadMoviesWithGenres() {
        Map<String, List<MoviesModel>> highestRatedMovies = new HashMap<>();
        String genre;
        String[] genres;
        List<MoviesModel> listOfMovies = ratingHandler.getMoviesWithAverageRatings();
        for (MoviesModel movie : listOfMovies) {
            genre = movie.getGenres();
            genres = genre.split("\\|");
            for (String singleGenre : genres) {
                if (!highestRatedMovies.containsKey(singleGenre)) {
                    List<MoviesModel> listOfMoviesWithGenre = new ArrayList<>();
                    listOfMoviesWithGenre.add(movie);
                    highestRatedMovies.put(singleGenre, listOfMoviesWithGenre);
                } else {
                    List<MoviesModel> listOfMoviesWithGenre = highestRatedMovies.get(singleGenre);
                    listOfMoviesWithGenre.add(movie);
                }
            }
        }
        return highestRatedMovies;
    }
    public Map<String, List<RecommendationModel>> loadRecommendationWithGenre() throws Exception {
        Map<String, List<RecommendationModel>> highestRatedMovies = new HashMap<>();
        String genre;
        String[] genres;
        List<RecommendationModel> listOfMovies = ratingHandler.getRecommendation();
        for (RecommendationModel movie : listOfMovies) {
            genre = movie.getGenres();
            genres = genre.split("\\|");
            for (String singleGenre : genres) {
                if (!highestRatedMovies.containsKey(singleGenre)) {
                    List<RecommendationModel> listOfMoviesWithGenre = new ArrayList<>();
                    listOfMoviesWithGenre.add(movie);
                    highestRatedMovies.put(singleGenre, listOfMoviesWithGenre);
                } else {
                    List<RecommendationModel> listOfMoviesWithGenre = highestRatedMovies.get(singleGenre);
                    listOfMoviesWithGenre.add(movie);
                }
            }
        }
        return highestRatedMovies;
    }
}

