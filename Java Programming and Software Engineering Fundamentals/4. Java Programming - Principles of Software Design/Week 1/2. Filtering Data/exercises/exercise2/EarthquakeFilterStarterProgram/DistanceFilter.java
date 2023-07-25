package exercise2.EarthquakeFilterStarterProgram;

public class DistanceFilter implements Filter {
    private Location referenceLocation;
    private double maxDist;
    private String filterName;
    
    public DistanceFilter(Location loc, double distance, String name){
        referenceLocation = loc;
        maxDist = distance;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe){
        if (qe.getLocation().distanceTo(referenceLocation) < maxDist) {
            return true;
        }
        return false;
    }

    public String getName(){
        return filterName;
    }

}
