package StepThreeJavaFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class FourthRatings {
    // private ArrayList<Movie> myMovies;
    // private ArrayList<EfficientRater> myRaters;
    // private HashMap<String, ArrayList<Double>> mapMovieIDToRatings;
    // private HashMap<String, String> mapMovieIDToMovieName;
    
    public FourthRatings() {
        // default constructor
        // this("ratedmoviesfull.csv", "ratings.csv");
        // this("ratings.csv");
    }

    public double getAverageByID(String id, int minimalRaters) {
        Double sumRating = 0.0;
        Integer countRating = 0;
        for (Rater r: RaterDatabase.getRaters()) {
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

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {

        ArrayList<String> listOfMovieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> listOfAvgMovieRatings = new ArrayList<Rating>();
        for (String movieID: listOfMovieIDs) {
            if (getAverageByID(movieID, minimalRaters) != 0) {
                listOfAvgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return listOfAvgMovieRatings;
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

    private double dotProduct(Rater me, Rater r){
        ArrayList<String> commonRatedMovies = new ArrayList<String>();
        for (String myRated: me.getItemsRated()) {
            if (r.getItemsRated().contains(myRated)) {
                commonRatedMovies.add(myRated);
            }
        }

        double dotProductOutput = 0.0;
        for (String commonMovie: commonRatedMovies){
            dotProductOutput += (me.getRating(commonMovie) - 5) * (r.getRating(commonMovie) - 5);
        }
        return dotProductOutput;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);

        ArrayList<Rating> similarityScore = new ArrayList<Rating>();
        for (Rater r: allRaters) {
            if (r.getID().equals(me.getID())) {
                continue;
            }
            similarityScore.add(new Rating(r.getID(), dotProduct(me, r)));
        }
        Collections.sort(similarityScore, Collections.reverseOrder());
        return similarityScore;
    }

    public double getAverageByIDSubset(String id, int minimalRaters, ArrayList<Rater> subsetRaters) {
        Double sumRating = 0.0;
        Integer countRating = 0;
        for (Rater r: subsetRaters) {
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

    public ArrayList<Rating> getAverageRatingsSubset(int minimalRaters, ArrayList<Rater> subsetRaters) {

        ArrayList<String> listOfMovieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> listOfAvgMovieRatings = new ArrayList<Rating>();
        for (String movieID: listOfMovieIDs) {
            if (getAverageByIDSubset(movieID, minimalRaters, subsetRaters) != 0) {
                listOfAvgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return listOfAvgMovieRatings;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        
        ArrayList<Rating> raterSimilarityScores = getSimilarities(id);
        HashMap<String, Double> mapRaterSubsetToSimilarityScore = new HashMap<String, Double>();
        int countSimilarRaters = 0;
        for (Rating r: raterSimilarityScores) {
            if (r.getValue() > 0 && countSimilarRaters < numSimilarRaters) {
                mapRaterSubsetToSimilarityScore.put(r.getItem(), r.getValue());
                countSimilarRaters += 1;
            }
        }
        
        double totalScore = 0.0;
        for (String rater: mapRaterSubsetToSimilarityScore.keySet()) {
            totalScore += mapRaterSubsetToSimilarityScore.get(rater);
        }

        for (String rater: mapRaterSubsetToSimilarityScore.keySet()) {
            mapRaterSubsetToSimilarityScore.put(
                rater, mapRaterSubsetToSimilarityScore.get(rater)/totalScore
            );
        }


        ArrayList<String> allMovies = MovieDatabase.filterBy(new TrueFilter());
        HashMap<String, ArrayList<Double>> mapRaterSubsetToRatings = new HashMap<String, ArrayList<Double>>();
        ArrayList<Double> raterRating = new ArrayList<Double>();
        for (String raterID: mapRaterSubsetToSimilarityScore.keySet()) {
            raterRating = new ArrayList<Double>();
            for (String m: allMovies) {
                raterRating.add(RaterDatabase.getRater(raterID).getRating(m));
            }
            mapRaterSubsetToRatings.put(raterID, raterRating);
        }

        ArrayList<Double> myRatings = new ArrayList<Double>();
        for (String m: allMovies) {
            myRatings.add(RaterDatabase.getRater(id).getRating(m));
        }
        
        // mapRaterSubsetToSimilarityScore
        HashMap<String, ArrayList<Double>> mapRaterToAdjustedRatings = new HashMap<String, ArrayList<Double>>();
        for (String raterID: mapRaterSubsetToRatings.keySet()) {
            ArrayList<Double> ratings = mapRaterSubsetToRatings.get(raterID);
            Double normalisedSimilarity = mapRaterSubsetToSimilarityScore.get(raterID);
            for (int i=0; i < ratings.size(); i++) {
                ratings.set(i, ratings.get(i) * normalisedSimilarity);
            }
            mapRaterToAdjustedRatings.put(raterID, ratings);
        }

        allMovies
    }
}
