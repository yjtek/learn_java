package StepThreeJavaFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;


public class FourthRatings {
    
    public FourthRatings() {
    }

    public double getAverageByID(String movieID, int minimalRaters) {
        Double sumMovieRating = 0.0;
        Integer countMovieRating = 0;
        for (Rater r: RaterDatabase.getRaters()) {
            if (r.getItemsRated().contains(movieID)) {
                sumMovieRating += r.getRating(movieID);
                countMovieRating++;
            }
        }

        if (countMovieRating.equals(0) || countMovieRating < minimalRaters) {
            return 0.0;
        }
        else {
            return sumMovieRating/countMovieRating;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {

        ArrayList<String> allMovieIDs = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> avgMovieRatings = new ArrayList<Rating>();
        for (String movieID: allMovieIDs) {
            if (getAverageByID(movieID, minimalRaters) != 0) {
                avgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return avgMovieRatings;
    }

    public ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<String> allMovieIDs = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> avgMovieRatings = new ArrayList<Rating>();
        for (String movieID: allMovieIDs) {
            if (getAverageByID(movieID, minimalRaters) != 0) {
                avgMovieRatings.add(new Rating(movieID, getAverageByID(movieID, minimalRaters)));
            }
        }
        return avgMovieRatings;
    }

    private double dotProduct(Rater me, Rater r){
        double dotProductOutput = 0.0;
        for (String currMovieID: me.getItemsRated()) {
            if (r.getItemsRated().contains(currMovieID)) {
                dotProductOutput += (me.getRating(currMovieID) - 5) * (r.getRating(currMovieID) - 5);
            }
        }
        return dotProductOutput;
    }

    private ArrayList<Rating> getSimilarities(String raterID) {
        ArrayList<Rater> allRaters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(raterID);

        ArrayList<Rating> similarityScore = new ArrayList<Rating>();
        for (Rater r: allRaters) {
            if (r.getID().equals(me.getID())) {
                continue;
            }
            double score = dotProduct(me, r);
            if (score > 0) {
                similarityScore.add(new Rating(r.getID(), score));
            }
        }
        Collections.sort(similarityScore, Collections.reverseOrder());
        return similarityScore;
    }

    public HashMap<String, Double> makeRaterIDToSimilarityScoreHashmap(ArrayList<Rating> raterSimilarityScores) {
        
        HashMap<String, Double> mapRaterIDToSimilarityScore = new HashMap<String, Double>();
        
        for (Rating r: raterSimilarityScores) {
            if (mapRaterIDToSimilarityScore.containsKey(r.getItem())) {
                System.exit(0);
            }
            mapRaterIDToSimilarityScore.put(r.getItem(), r.getValue());
        }
        return mapRaterIDToSimilarityScore;
    }

    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> movieIDToWeightedAverageRating = new ArrayList<Rating>();
        
        ArrayList<Rating> raterSubsetSimilarityScores = new ArrayList<>(getSimilarities(raterID).subList(0, numSimilarRaters));
        HashMap<String, Double> mapRaterSubsetToSimilarityScore = makeRaterIDToSimilarityScoreHashmap(raterSubsetSimilarityScores);

        ArrayList<String> allMovieID = MovieDatabase.filterBy(new TrueFilter());

        // HashMap<String, Double> mapMovieIDToAdjRatings = new HashMap<String, Double>();
        for (String movieID: allMovieID) {

            Double adjMovieRatingSum = 0.0;
            int countMovieRating = 0;
            Double weightedAverageRating;
            
            for (String currRaterID: mapRaterSubsetToSimilarityScore.keySet()) {
                Rater currRater = RaterDatabase.getRater(currRaterID);
                Double currRaterSimilarityScore = mapRaterSubsetToSimilarityScore.get(currRaterID);
                Double currRaterAdjMovieRating = currRater.getRating(movieID) * currRaterSimilarityScore;
                
                // Only add to the summation if the rating is greater than 0 (because if movie is not rated, it returns -1)
                if (currRaterAdjMovieRating > 0) {
                    adjMovieRatingSum += currRaterAdjMovieRating;
                    countMovieRating++;
                }

            }
            
            if (countMovieRating != 0 && countMovieRating >= minimalRaters) {
                weightedAverageRating = adjMovieRatingSum/countMovieRating;
                Rating addCurrRating = new Rating(movieID, weightedAverageRating);
                movieIDToWeightedAverageRating.add(addCurrRating);
            }
        }

        Collections.sort(movieIDToWeightedAverageRating, Collections.reverseOrder());
        
        return movieIDToWeightedAverageRating;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> movieIDToWeightedAverageRating = new ArrayList<Rating>();
        
        ArrayList<Rating> raterSubsetSimilarityScores = new ArrayList<>(getSimilarities(raterID).subList(0, numSimilarRaters));
        HashMap<String, Double> mapRaterSubsetToSimilarityScore = makeRaterIDToSimilarityScoreHashmap(raterSubsetSimilarityScores);

        ArrayList<String> allMovieID = MovieDatabase.filterBy(f);

        // HashMap<String, Double> mapMovieIDToAdjRatings = new HashMap<String, Double>();
        for (String movieID: allMovieID) {

            Double adjMovieRatingSum = 0.0;
            int countMovieRating = 0;
            Double weightedAverageRating;
            
            for (String currRaterID: mapRaterSubsetToSimilarityScore.keySet()) {
                Rater currRater = RaterDatabase.getRater(currRaterID);
                Double currRaterSimilarityScore = mapRaterSubsetToSimilarityScore.get(currRaterID);
                Double currRaterAdjMovieRating = currRater.getRating(movieID) * currRaterSimilarityScore;
                
                // Only add to the summation if the rating is greater than 0 (because if movie is not rated, it returns -1)
                if (currRaterAdjMovieRating > 0) {
                    adjMovieRatingSum += currRaterAdjMovieRating;
                    countMovieRating++;
                }

            }
            
            if (countMovieRating != 0 && countMovieRating >= minimalRaters) {
                weightedAverageRating = adjMovieRatingSum/countMovieRating;
                Rating addCurrRating = new Rating(movieID, weightedAverageRating);
                movieIDToWeightedAverageRating.add(addCurrRating);
            }
        }

        Collections.sort(movieIDToWeightedAverageRating, Collections.reverseOrder());
        
        return movieIDToWeightedAverageRating;
    }
}
