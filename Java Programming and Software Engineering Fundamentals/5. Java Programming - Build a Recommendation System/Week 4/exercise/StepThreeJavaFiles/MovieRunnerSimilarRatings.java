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

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");

        ArrayList<Rating> similarMovies = fr.getSimilarRatings("71", 20, 5);
        for (Rating r: similarMovies) {
            System.out.println("Movie name + Rating info: " + MovieDatabase.getTitle(r.getItem()) + " " + r);
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");

        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));
        for (Rating r: similarMovies) {
            System.out.println(
                "Movie name + Rating info + Genre: " + 
                MovieDatabase.getTitle(r.getItem()) + " " + r + " " + 
                MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");

        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter("120", 10, 2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        for (Rating r: similarMovies) {
            System.out.println(
                "Movie name + Rating info + Directors: " + 
                MovieDatabase.getTitle(r.getItem()) + " " + r + " " + 
                MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");

        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter("Drama"));
        af.addFilter(new MinuteFilter(80, 160));

        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter("168", 10, 3, af);
        for (Rating r: similarMovies) {
            System.out.println(
                "Movie name + Rating info + Minutes + Genres: " + 
                MovieDatabase.getTitle(r.getItem()) + " " + r + " " + 
                MovieDatabase.getMinutes(r.getItem()) + " " + 
                MovieDatabase.getGenres(r.getItem())
            );
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        
        // MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");

        // RaterDatabase.initialize("ratings_short.csv");
        RaterDatabase.initialize("ratings.csv");

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(1975));
        af.addFilter(new MinuteFilter(70, 200));

        ArrayList<Rating> similarMovies = fr.getSimilarRatingsByFilter("314", 10, 5, af);
        for (Rating r: similarMovies) {
            System.out.println(
                "Movie name + Rating info + Minutes + Genres: " + 
                MovieDatabase.getTitle(r.getItem()) + " " + r + " " + 
                MovieDatabase.getMinutes(r.getItem()) + " " + 
                MovieDatabase.getGenres(r.getItem())
            );
        }
    }
}
