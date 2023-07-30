/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import StepOneStarterProgram.*;;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private HashMap<String, ArrayList<Double>> mapMovieIDToRatings;
    private HashMap<String, String> mapMovieIDToMovieName;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
        mapMovieIDToRatings = fr.mapMovieIDToRatings(myRaters);
        mapMovieIDToMovieName = makeMovieIDToMovieNameMap(myMovies);
    }

    public int getMovieSize(){
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        Double totalRating = 0.0;
        if (mapMovieIDToRatings.get(id).size() >= minimalRaters) {
            for (Double rating: mapMovieIDToRatings.get(id)) {
                totalRating += rating;
            }
            return totalRating/mapMovieIDToRatings.get(id).size();
        }
        else {
            return 0.0;
        }
    }

    private double getArrayTotal(ArrayList<Double> ratings) {
        Double arrayTotal = 0.0;
        for (Double d: ratings) {
            arrayTotal += d;
        }
        return arrayTotal;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRating = new ArrayList<Rating>();
        for (String movieID: mapMovieIDToRatings.keySet()) {
            if (mapMovieIDToRatings.get(movieID).size() < minimalRaters) {
                continue;
            }
            Rating currRating = new Rating(movieID, getAverageByID(movieID, minimalRaters));
            averageRating.add(currRating);
        }
        return averageRating;
    }

    private HashMap<String, String> makeMovieIDToMovieNameMap(ArrayList<Movie> myMovies) {
        
        HashMap<String, String> movieIDToMovieNameMap = new HashMap<String, String>();
        
        for (Movie m: myMovies) {
            if (movieIDToMovieNameMap.containsKey(m.getID())) {
                continue;
            }
            else {
                movieIDToMovieNameMap.put(m.getID(), m.getTitle());
            }
        }
        return movieIDToMovieNameMap;
    }

    public String getTitle(String id) {
        if (mapMovieIDToMovieName.containsKey(id)) {
            return mapMovieIDToMovieName.get(id);
        }
        else {
            return "MovieID not found";
        }
    }

    public String getID(String title){
        for (String movieID: mapMovieIDToMovieName.keySet()) {
            if (mapMovieIDToMovieName.get(movieID).equals(title)) {
                return movieID;
            }
        }
        return "NO SUCH TITLE";
    }
    
}