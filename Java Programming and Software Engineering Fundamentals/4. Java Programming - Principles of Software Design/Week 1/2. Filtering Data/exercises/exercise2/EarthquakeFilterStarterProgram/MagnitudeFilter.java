package exercise2.EarthquakeFilterStarterProgram;

public class MagnitudeFilter implements Filter {
    private double minMagFilter;
    private double maxMagFilter;
    private String filterName;
    
    public MagnitudeFilter(double minMag, double maxMag, String name) {
        minMagFilter = minMag;
        maxMagFilter = maxMag;
        filterName = name;
    }

    public boolean satisfies(QuakeEntry qe) {
        if ((qe.getMagnitude() >= minMagFilter) && (qe.getMagnitude() <= maxMagFilter)) {
            return true;
        }
        return false;
    }

    public String getName(){
        return filterName;
    }


}
