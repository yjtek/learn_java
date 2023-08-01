import java.util.*;

public class RecommenderRunner implements Recommender {
    public ArrayList<String> getItemsToRate() {

        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(2015));
        
        ArrayList<String> someMovieIDs = new ArrayList<>(MovieDatabase.filterBy(af).subList(0, 20));
        return someMovieIDs;
    }

    public void printRecommendationsFor(String webRaterID) {
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(webRaterID, 20, 10, new TrueFilter());
        for (Rating r: similarRatings) {
            System.out.println(
                "Movie name + Rating info: " + 
                MovieDatabase.getTitle(r.getItem()) + " " + r
            );
        }
    }
}
