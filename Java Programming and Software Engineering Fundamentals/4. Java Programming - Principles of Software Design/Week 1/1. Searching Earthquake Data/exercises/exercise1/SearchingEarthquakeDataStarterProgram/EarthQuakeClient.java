package exercise1.SearchingEarthquakeDataStarterProgram;

import java.util.*;

import exercise1.edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            if (qe.getLocation().distanceTo(from) < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            if ((qe.getDepth() >= (minDepth*1000)) && (qe.getDepth() <= (maxDepth*1000))){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe: quakeData){
            String title = qe.getInfo();
            if (where == "start") {
                if (title.startsWith(phrase)) {
                    answer.add(qe);
                }
                else {
                    continue;
                }
            }
            else if (where == "end") {
                if (title.endsWith(phrase)) {
                    answer.add(qe);
                }
                else {
                    continue;
                }
            } 
            else if (where == "any") {
                if (title.contains(phrase)) {
                    answer.add(qe);
                }
                else {
                    continue;
                }
            }
            else {
                break;
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        // String source = "data/nov20quakedata.atom";
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listFiltered = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);
        Location city = new Location(19.2859993, -155.2333374);
        
        // This location is Bridgeport, CA
        // Location city =  new Location(38.17, -118.82);

        // TODO
        ArrayList<QuakeEntry> listFiltered = filterByDistanceFrom(list, 20000000, city);
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listFiltered = filterByDepth(list, -10000, 10000);
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> listFiltered = filterByPhrase(list, "end", "California");
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }
        
        System.out.println("+++++++++++++++++++++");
        listFiltered = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }

        System.out.println("+++++++++++++++++++++");
        listFiltered = filterByPhrase(list, "start", "Explosion");
        for (QuakeEntry qe: listFiltered){
            System.out.println(qe);
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
