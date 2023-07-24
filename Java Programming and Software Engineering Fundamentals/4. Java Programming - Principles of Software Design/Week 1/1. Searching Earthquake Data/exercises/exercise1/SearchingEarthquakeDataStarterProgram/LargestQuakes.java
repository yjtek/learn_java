package exercise1.SearchingEarthquakeDataStarterProgram;

import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(String source){
        EarthQuakeParser parser = new EarthQuakeParser();
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size());

        // for (QuakeEntry qe: list){
            // System.out.println(qe);
        // }
        System.out.println("Count of QuakeEntry found: " + list.size());
        indexOfLargest(list);

        ArrayList<QuakeEntry> largestQuakes = getLargest(list, 5);
        for (QuakeEntry qe: largestQuakes) {
            System.out.println(qe);
        }
    }

    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        double maxMag = 0.0;
        int maxMagIndex = 0;
        for (int i=0; i<data.size(); i++) {
            double magnitude = data.get(i).getMagnitude();
            if (magnitude > maxMag){
                maxMag = magnitude;
                maxMagIndex = i;
            }
        }
        System.out.println("Maximum magnitude " + maxMag + " at index " + maxMagIndex);
        return maxMagIndex;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<QuakeEntry>(quakeData);
        for (int i=0; i<howMany; i++){
            int index = indexOfLargest(quakeDataCopy);
            answer.add(quakeDataCopy.get(index));
            quakeDataCopy.remove(index);
            if (quakeDataCopy.size() == 0) {
                return answer;
            }
        }
        return answer;
    }
}
