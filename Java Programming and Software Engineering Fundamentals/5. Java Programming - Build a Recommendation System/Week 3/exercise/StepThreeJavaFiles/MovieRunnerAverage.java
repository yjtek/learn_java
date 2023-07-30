package StepThreeJavaFiles;

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        // SecondRatings sr = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");

        // System.out.println(sr.getMovieSize());
        // System.out.println(sr.getRaterSize());
        // System.out.println(sr.getAverageByID("0068646", 1));
        
        ArrayList<Rating> ar = sr.getAverageRatings(12);
        System.out.println(ar.size());
        Collections.sort(ar);
        for (Rating r: ar){
            System.out.println(r + ": " + sr.getTitle(r.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String movieID = sr.getID("Vacation");
        System.out.println(sr.getAverageByID(movieID, 1));
    }
}
