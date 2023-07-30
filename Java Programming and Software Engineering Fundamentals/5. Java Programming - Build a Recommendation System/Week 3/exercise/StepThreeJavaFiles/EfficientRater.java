package StepThreeJavaFiles;

import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }

    public void addRating(String item, double rating) {
        Rating newRating = new Rating(item,rating);
        myRatings.put(item, newRating);
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)) {
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        // for(int k=0; k < myRatings.size(); k++){
        //     if (myRatings.get(k).getItem().equals(item)){
        //         return myRatings.get(k).getValue();
        //     }
        // }

        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        // for(int k=0; k < myRatings.size(); k++){
        //     list.add(myRatings.get(k).getItem());
        // }
        for (String key: myRatings.keySet()) {
            list.add(key);
        }
        return list;
    }

    public String toString() {
        return "User ID: " + myID;
    }
}
