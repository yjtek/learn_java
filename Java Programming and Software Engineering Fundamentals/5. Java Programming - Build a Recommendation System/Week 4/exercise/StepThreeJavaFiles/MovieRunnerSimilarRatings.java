package StepThreeJavaFiles;

import java.util.ArrayList;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings tr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("RaterDatabase size: " + RaterDatabase.size());
        
        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        for (Rating r: ratings) {
            System.out.println("Movie + Rating: " + MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings tr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("MovieDatabase size: " + MovieDatabase.size());

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("RaterDatabase size: " + RaterDatabase.size());

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));

        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(8, af);
        for (Rating r: ratings) {
            String movieID = r.getItem();
            System.out.println(
                "Movie + Rating + Year + Genre: " + 
                MovieDatabase.getTitle(movieID) + " || " +
                r.getValue() + " || " +
                MovieDatabase.getYear(movieID) + " || " +
                MovieDatabase.getGenres(movieID)
            );
        }    
        System.out.println(ratings.size());    
    }
}
