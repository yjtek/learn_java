package exercise1.EarthquakeFilterStarterProgram;

public class MagnitudeFilter implements Filter {
    private double minMagFilter;
    private double maxMagFilter;
    
    public MagnitudeFilter(double minMag, double maxMag) {
        minMagFilter = minMag;
        maxMagFilter = maxMag;
    }

    public boolean satisfies(QuakeEntry qe) {
        if ((qe.getMagnitude() >= minMagFilter) && (qe.getMagnitude() <= maxMagFilter)) {
            return true;
        }
        return false;
    }

}
