import StepThreeJavaFiles.*;

public class exercise {
    
    public static void main(String[] args){
        // FirstRatings fr = new FirstRatings();
        // fr

        // MovieRunnerAverage mra = new MovieRunnerAverage();
        // mra.printAverageRatings();
        // mra.getAverageRatingOneMovie();

        MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
        // mrwf.printAverageRatings();
        // mrwf.printAverageRatingsByYear();
        // mrwf.printAverageRatingsByGenre();
        // mrwf.printAverageRatingsByMinutes();
        // mrwf.printAverageRatingsByDirectors();
        // mrwf.printAverageRatingsByYearAfterAndGenre();
        mrwf.printAverageRatingsByDirectorsAndMinutes();

    }
}
