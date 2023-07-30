package StepThreeJavaFiles;

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        // ThirdRatings tr = new ThirdRatings("ratings.csv");
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatings(1);
        for (Rating r: ratings) {
            System.out.println("Movie + Rating: " + MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }

        // System.out.println(sr.getMovieSize());
        // System.out.println(sr.getRaterSize());
        // System.out.println(sr.getAverageByID("0068646", 1));
        
        // ArrayList<Rating> ar = sr.getAverageRatings(12);
        // System.out.println(ar.size());
        // Collections.sort(ar);
        // for (Rating r: ar){
        //     System.out.println(r + ": " + sr.getTitle(r.getItem()));
        // }
    }
}