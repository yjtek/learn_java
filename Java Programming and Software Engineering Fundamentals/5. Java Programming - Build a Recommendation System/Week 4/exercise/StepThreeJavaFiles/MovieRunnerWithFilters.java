package StepThreeJavaFiles;

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatings(35);
        for (Rating r: ratings) {
            System.out.println("Movie + Rating: " + MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());


        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(20, new YearAfterFilter(2000));
        for (Rating r: ratings) {
            System.out.println("Movie + Rating: " + MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(20, new GenreFilter("Comedy"));
        for (Rating r: ratings) {
            String movieID = r.getItem();
            System.out.println(
                "Movie + Rating + Genre: " + 
                MovieDatabase.getTitle(movieID) + " || " +
                r.getValue() + " || " +
                MovieDatabase.getGenres(movieID) 
            );
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(5, new MinuteFilter(105,135));
        for (Rating r: ratings) {
            String movieID = r.getItem();
            System.out.println(
                "Movie + Rating + Minutes: " + 
                MovieDatabase.getTitle(movieID) + " || " +
                r.getValue() + " || " +
                MovieDatabase.getMinutes(movieID)
            );
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(4, new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));
        for (Rating r: ratings) {
            String movieID = r.getItem();
            System.out.println(
                "Movie + Rating + Directors: " + 
                MovieDatabase.getTitle(movieID) + " || " +
                r.getValue() + " || " +
                MovieDatabase.getDirector(movieID)
            );
        }
        System.out.println(ratings.size());
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        // ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("Number of raters: " + tr.getRaterSize());

        MovieDatabase.initialize("ratedmoviesfull.csv");
        // MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("Number of movies in database: " + MovieDatabase.size());

        AllFilters af = new AllFilters();
        af.addFilter(new MinuteFilter(90, 180));
        af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));

        ArrayList<Rating> ratings = tr.getAverageRatingByFilter(3, af);
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