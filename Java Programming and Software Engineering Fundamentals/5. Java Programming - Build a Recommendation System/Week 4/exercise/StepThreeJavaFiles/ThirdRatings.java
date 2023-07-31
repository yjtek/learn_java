package StepThreeJavaFiles;

import java.util.*;

public class ThirdRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    private HashMap<String, ArrayList<Double>> mapMovieIDToRatings;
    private HashMap<String, String> mapMovieIDToMovieName;
    
    public ThirdRatings() {
        // default constructor
        // this("ratedmoviesfull.csv", "ratings.csv");
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings fr = new FirstRatings();
        // myMovies = fr.loadMovies(movieFile);
        myRaters = fr.loadRaters(ratingsFile);
        // mapMovieIDToRatings = fr.mapMovieIDToRatings(myRaters);
        // mapMovieIDToMovieName = makeMovieIDToMovieNameMap(myMovies);
    }

    // public int getMovieSize(){
    //     return myMovies.size();
    // }

    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        Double sumRating = 0.0;
        Integer countRating = 0;
        for (Rater r: myRaters) {
            if (r.getItemsRated().contains(id)) {
                sumRating += r.getRating(id);
                countRating++;
            }
        }

        if (countRating.equals(0) || countRating < minimalRaters) {
            return 0.0;
        }
        else {
            return sumRating/countRating;
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

        ArrayList<String> listOfMovieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> listOfAvgMovieRatings = new ArrayList<Rating>();
        for (String movieID: listOfMovieIDs) {
            if (getAverageByID(movieID, minimalRaters) != 0) {
                listOfAvgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return listOfAvgMovieRatings;
        // ArrayList<Rating> averageRating = new ArrayList<Rating>();
        // for (String movieID: mapMovieIDToRatings.keySet()) {
        //     if (mapMovieIDToRatings.get(movieID).size() < minimalRaters) {
        //         continue;
        //     }
        //     Rating currRating = new Rating(movieID, getAverageByID(movieID, minimalRaters));
        //     averageRating.add(currRating);
        // }
        // return averageRating;
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

    public ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> listOfMovieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> listOfAvgMovieRatings = new ArrayList<Rating>();
        for (String movieID: listOfMovieIDs) {
            if (getAverageByID(movieID, minimalRaters) != 0) {
                listOfAvgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return listOfAvgMovieRatings;

    }
    
}