import edu.duke.*;
import StepOneStarterProgram.*;
import StepOneStarterProgram.data.*;
import java.util.*;

import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movieData = new ArrayList<Movie>();
        
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord r: parser){
            Movie currMovie = new Movie(
                r.get("id"),
                r.get("title"),
                r.get("year"),
                r.get("genre"),
                r.get("director"),
                r.get("country"),
                r.get("poster"),
                Integer.parseInt(r.get("minutes"))
            );
            movieData.add(currMovie);
        }
        return movieData;
    }

    private void printArrayListData(ArrayList<?> arrayListData) {
        for (Object o: arrayListData) {
            System.out.println(o);
        }
    }

    private int countComedyMovies(ArrayList<Movie> movieData){
        int countComedy = 0;
        for (Movie m: movieData) {
            if (m.getGenres().contains("Comedy")) {
                countComedy++;
            }
        }
        return countComedy;
    }

    private int countMoviesLongerThanMinutes(ArrayList<Movie> movieData, int minutes){
        int countLongerThan = 0;
        for (Movie m: movieData) {
            if (m.getMinutes() > minutes) {
                countLongerThan++;
            }
        }
        return countLongerThan;
    }

    private HashMap<String, Integer> mapDirectorToMovieCount(ArrayList<Movie> movieData) {
        HashMap<String, Integer> countMoviesPerDirector = new HashMap<String, Integer>();
        for (Movie m: movieData) {
            String allDirectors = m.getDirector();
            String[] allDirectorsSplit = allDirectors.split(", ");
            for (String director: allDirectorsSplit) {
                if (countMoviesPerDirector.containsKey(director)) {
                    countMoviesPerDirector.put(director, countMoviesPerDirector.get(director) + 1);
                }
                else {
                    countMoviesPerDirector.put(director, 1);
                }
            }
        }
        return countMoviesPerDirector;
    }

    private int getMaxMovieCount(HashMap<String, Integer> countMoviesPerDirector) {
        int maxMovieCount = 0;
        for (String dir: countMoviesPerDirector.keySet()){
            if (countMoviesPerDirector.get(dir) > maxMovieCount) {
                maxMovieCount = countMoviesPerDirector.get(dir);
            }
        }
        return maxMovieCount;
    }

    private ArrayList<String> getDirectorsWithMaxMovieCount(HashMap<String, Integer> countMoviesPerDirector, int maxMovieCount) {
        ArrayList<String> dirsWithMaxMovs = new ArrayList<String>();
        for (String dir: countMoviesPerDirector.keySet()){
            if (countMoviesPerDirector.get(dir) == maxMovieCount) {
                dirsWithMaxMovs.add(dir);
            }
        }
        return dirsWithMaxMovs;
    }

    public void testLoadMovies() {
        // ArrayList<Movie> movieData = loadMovies("ratedmovies_short.csv");
        ArrayList<Movie> movieData = loadMovies("ratedmoviesfull.csv");

        // printArrayListData(movieData);
        
        System.out.println("Count movies found: " + movieData.size());
        System.out.println("Count comedies found: " + countComedyMovies(movieData));
        System.out.println("Count shows > 150min: " + countMoviesLongerThanMinutes(movieData, 150));

        HashMap<String, Integer> countMoviesPerDirector = mapDirectorToMovieCount(movieData);
        int maxMovieCount = getMaxMovieCount(countMoviesPerDirector);
        ArrayList<String> dirsWithMaxMovieCount = getDirectorsWithMaxMovieCount(countMoviesPerDirector, maxMovieCount);
        
        System.out.println("Maximum number of movies directed: " + maxMovieCount);
        System.out.println("Directors with maximum number of movies directed: ");
        printArrayListData(dirsWithMaxMovieCount);
    }

    public ArrayList<Rater> loadRaters(String filename){
        HashMap<String, Rater> mapRaterToRatings = new HashMap<String, Rater>();
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for (CSVRecord r: parser) {
            String raterID = r.get(0);
            
            if (mapRaterToRatings.containsKey(raterID)) {
                Rater existingRater = mapRaterToRatings.get(raterID);
                existingRater.addRating(r.get(1), Double.parseDouble(r.get(2)));;
                mapRaterToRatings.put(raterID, existingRater);
            }
            else {
                Rater newRater = new Rater(raterID);
                newRater.addRating(r.get(1), Double.parseDouble(r.get(2)));
                mapRaterToRatings.put(raterID, newRater);
            }
        }

        for (String raterID: mapRaterToRatings.keySet()) {
            raterList.add(mapRaterToRatings.get(raterID));
        }
        return raterList;
    }

    public void testLoadRaters() {
        // ArrayList<Rater> raterList = loadRaters("ratings_short.csv");
        ArrayList<Rater> raterList = loadRaters("ratings.csv");
        System.out.println("Rater list has size: " + raterList.size());
        // printRaterAndRatingCount(raterList);
        printRaterAndRatingCount(raterList, "193");

        System.out.println("Maximum rating count is: " + getMaxRatingCount(raterList));
        System.out.println("Users with max rating count are: ");
        printArrayListData(getMaxRatingCountRaters(raterList));

        HashMap<String, Integer> movieIDToRatingCountMap = mapMovieIDToRatingCount(raterList);
        System.out.println("Count of ratings for MoveID 1798709: " + getCountOfRatings(movieIDToRatingCountMap, "1798709"));
        System.out.println("Count of movies rated total: " + getCountOfMoviesRated(movieIDToRatingCountMap));
    }

    private void printRaterAndRatingCount(ArrayList<Rater> raterList) {
        for (Rater r: raterList) {
            System.out.println("Rater " + r.getID() + " || " + "Count ratings: " + r.numRatings());
        }
    }

    private void printRaterAndRatingCount(ArrayList<Rater> raterList, String raterID) {
        for (Rater r: raterList) {
            if (r.getID().equals(raterID)){
                System.out.println("Rater " + r.getID() + " || " + "Count ratings: " + r.numRatings());
            }
        }
    }

    private int getMaxRatingCount(ArrayList<Rater> raterList){
        int maxCount = 0;
        for (Rater r: raterList) {
            if (r.numRatings() > maxCount) {
                maxCount = r.numRatings();
            }
        }
        return maxCount;
    }

    private ArrayList<Rater> getMaxRatingCountRaters(ArrayList<Rater> raterList){
        ArrayList<Rater> raterListSubset = new ArrayList<Rater>();
        int maxCount = getMaxRatingCount(raterList);
        for (Rater r: raterList) {
            if (r.numRatings() == maxCount) {
                raterListSubset.add(r);
            }
        }
        return raterListSubset;
    }

    private HashMap<String, Integer> mapMovieIDToRatingCount(ArrayList<Rater> raterList) {
        HashMap<String, Integer> movieIDToRatingCount = new HashMap<String, Integer>();
        for (Rater r: raterList) {
            for (String ratedItem: r.getItemsRated()) {
                if (movieIDToRatingCount.containsKey(ratedItem)) {
                    movieIDToRatingCount.put(ratedItem, movieIDToRatingCount.get(ratedItem) + 1);
                }
                else {
                    movieIDToRatingCount.put(ratedItem, 1);
                }
            }
        }
        return movieIDToRatingCount;
    }
    
    private int getCountOfRatings(HashMap<String, Integer> movieIDToRatingCountMap, String movieID) { 
        return movieIDToRatingCountMap.get(movieID);
    }

    private int getCountOfMoviesRated(HashMap<String, Integer> movieIDToRatingCountMap) {
        return movieIDToRatingCountMap.size();
    }

}
