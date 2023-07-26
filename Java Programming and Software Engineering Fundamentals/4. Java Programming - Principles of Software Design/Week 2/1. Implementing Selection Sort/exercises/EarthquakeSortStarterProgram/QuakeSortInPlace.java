package EarthquakeSortStarterProgram;
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
        
    }

    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        // String source = "data/nov20quakedata.atom";
        // String source = "nov20quakedatasmall.atom";
        // String source = "earthquakeDataSampleSix1.atom";
        // String source = "earthquakeDataSampleSix2.atom";
        // String source = "earthQuakeDataDec6sample1.atom";
        // String source = "earthQuakeDataDec6sample2.atom";
        String source = "earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
        
        // sortByMagnitude(list);
        sortByLargestDepth(list);
        // sortByMagnitudeWithBubbleSortWithCheck(list);
        // sortByMagnitudeWithCheck(list);

        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        
        Integer maxDepthIndex = null;

        for (int i=from; i<quakeData.size(); i++){
            if (maxDepthIndex == null) {
                maxDepthIndex = i;
                
            }
            else if (quakeData.get(i).getDepth() > quakeData.get(maxDepthIndex).getDepth()) {
                maxDepthIndex = i;
            }
        }
        return maxDepthIndex;
    }

    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        // for (int i=0; i < in.size(); i++) {
        // for (int i=0; i < 50; i++) {
        for (int i=0; i < 70; i++) {
            int minIdx = getLargestDepth(in, i);
            QuakeEntry currEntry = in.get(i);
            QuakeEntry minEntry = in.get(minIdx);
            in.set(i, minEntry);
            in.set(minIdx, currEntry);
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        // System.out.println("++++++++++++++++++++++++");
        // System.out.println("Start one pass");
        for (int i=1; (i < (quakeData.size() - numSorted)); i++) {
            // System.out.println("++++++");
            // System.out.println("Loop " + i);
            // System.out.println(quakeData.size() - numSorted);

            QuakeEntry prev = quakeData.get(i-1);
            QuakeEntry curr = quakeData.get(i);
            if (prev.getMagnitude() > curr.getMagnitude()) {
                // System.out.println(prev.getMagnitude());
                // System.out.println(curr.getMagnitude());
                quakeData.set(i, prev);
                quakeData.set(i-1, curr);
            }            
        }
        System.out.println("Printing quakes after pass " + numSorted);
        // for (QuakeEntry qe: quakeData) {
        //     System.out.println(qe);
        // }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for (QuakeEntry qe: in) {
            System.out.println(qe);
        }
        
        for (int i=0; i < in.size()-1; i++){
            onePassBubbleSort(in, i);
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i=1; i<quakes.size(); i++){
            if (quakes.get(i).getMagnitude() < quakes.get(i-1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i<in.size(); i++){
            onePassBubbleSort(in, i);
            if (checkInSortedOrder(in)) {
                System.out.println("Count bubble sorts done: " + (i+1));
                break;
            }

        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            if (checkInSortedOrder(in)) {
                System.out.println("Count bubble sorts done: " + (i+1));
                break;
            }
        }
    }
}
