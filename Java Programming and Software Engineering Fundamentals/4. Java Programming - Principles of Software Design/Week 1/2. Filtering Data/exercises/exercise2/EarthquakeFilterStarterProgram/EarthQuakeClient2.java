package exercise2.EarthquakeFilterStarterProgram;

import java.util.*;
import exercise2.edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        // String source = "data/nov20quakedatasmall.atom";
        // String source = "nov20quakedatasmall.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        // Filter f = new MinMagFilter(4.0);         
        // ArrayList<QuakeEntry> m7  = filter(list, f); 
        // for (QuakeEntry qe: m7) { 
        //     System.out.println(qe);
        // } 

        // Filter f = new DepthFilterExcl(-12_000, -10_000, "depthfilter_-12k_-10k");         
        // ArrayList<QuakeEntry> filteredList  = filter(list, f); 
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 

        Filter f = new DepthFilterExcl(-4_000, -2_000, "depthfilter_-4k_-2k");         
        ArrayList<QuakeEntry> filteredList  = filter(list, f); 
        for (QuakeEntry qe: filteredList) { 
            System.out.println(qe);
        } 

        // Filter f = new PhraseFilter("start", "Quarry Blast", "phrasefilter_start_QuarryBlast");         
        // ArrayList<QuakeEntry> filteredList  = filter(list, f); 
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 

        // Filter f = new PhraseFilter("end", "Alaska", "phrasefilter_end_Alaska");         
        // ArrayList<QuakeEntry> filteredList  = filter(list, f); 
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 

        // Filter f = new PhraseFilter("any", "Can", "phrasefilter_any_Can");
        // ArrayList<QuakeEntry> filteredList  = filter(list, f); 
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 

        // Filter f1 = new MagnitudeFilter(4.0, 5.0, "magnitudefilter_4_5");
        // Filter f2 = new DepthFilter(-35000.0, -12000.0, "depthfilter_-35k_-12k");
        // ArrayList<QuakeEntry> filteredList = filter(list, f1);
        // filteredList = filter(filteredList, f2);
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 

        // Location tokyo = new Location(35.42, 139.43);
        // Filter f1 = new DistanceFilter(tokyo, 10_000_000, "distancefilter_tokyo_10m");
        // Filter f2 = new PhraseFilter("end", "Japan", "phrasefilter_end_Japan");
        // ArrayList<QuakeEntry> filteredList = filter(list, f1);
        // filteredList = filter(filteredList, f2);
        // for (QuakeEntry qe: filteredList){
        //     System.out.println(qe);
        // }

        // Location denver = new Location(39.7392, -104.9903);
        // Filter f1 = new DistanceFilter(denver, 1_000_000, "distancefilter_denver_1m");
        // Filter f2 = new PhraseFilter("end", "a", "phrasefilter_end_a");
        // ArrayList<QuakeEntry> filteredList = filter(list, f1);
        // filteredList = filter(filteredList, f2);
        // for (QuakeEntry qe: filteredList){
        //     System.out.println(qe);
        // }

        // Filter f1 = new MagnitudeFilter(3.5, 4.5, "magnitudefilter_35_45");
        // Filter f2 = new DepthFilter(-55000.0, -20000.0, "depthfilter_-55k_-20k");
        // ArrayList<QuakeEntry> filteredList = filter(list, f1);
        // filteredList = filter(filteredList, f2);
        // for (QuakeEntry qe: filteredList) { 
        //     System.out.println(qe);
        // } 


        System.out.println(filteredList.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "nov20quakedatasmall.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        // maf.addFilter(new MagnitudeFilter(0.0, 2.0, "magnitudefilter_0_2"));
        // maf.addFilter(new DepthFilter(-100_000, -10_000, "depthfilter_-100k_-10l"));
        // maf.addFilter(new PhraseFilter("any", "a", "phrasefilter_any_a"));

        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "magnitudefilter_1_4"));
        maf.addFilter(new DepthFilter(-180_000, -30_000, "depthfilter_-180k_-30l"));
        maf.addFilter(new PhraseFilter("any", "o", "phrasefilter_any_o"));


        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe: filteredList) {
            System.out.println(qe);
        }
        
        System.out.println(filteredList.size());
        System.out.println("Filters are: " + maf.getName());
    }

    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser();
        // String source = "nov20quakedatasmall.atom";
        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        // maf.addFilter(new MagnitudeFilter(0.0, 3.0, "magnitudefilter_0_3"));
        // maf.addFilter(new DistanceFilter(new Location(36.1314, -95.9372), 10_000_000, "distancefilter_10m"));
        // maf.addFilter(new PhraseFilter("any", "Ca", "phrasefilter_any_Ca"));

        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "magnitudefilter_0_5"));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3_000_000, "distancefilter_3m"));
        maf.addFilter(new PhraseFilter("any", "e", "phrasefilter_any_e"));

        ArrayList<QuakeEntry> filteredList = filter(list, maf);
        for (QuakeEntry qe: filteredList) {
            System.out.println(qe);
        }
        
        System.out.println(filteredList.size());
        System.out.println("Filters are: " + maf.getName());
    }

}
