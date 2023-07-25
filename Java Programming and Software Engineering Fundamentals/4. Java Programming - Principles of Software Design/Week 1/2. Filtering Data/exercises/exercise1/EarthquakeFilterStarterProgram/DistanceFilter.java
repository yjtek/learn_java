package exercise1.EarthquakeFilterStarterProgram;

public class DistanceFilter implements Filter {
    private Location referenceLocation;
    private double maxDist;
    
    public DistanceFilter(Location loc, double distance){
        referenceLocation = loc;
        maxDist = distance;
    }

    public boolean satisfies(QuakeEntry qe){
        if (qe.getLocation().distanceTo(referenceLocation) < maxDist) {
            return true;
        }
        return false;
    }
}
