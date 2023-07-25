package exercise2.EarthquakeFilterStarterProgram;

public class DepthFilter implements Filter {

    private double minDepthFilter;
    private double maxDepthFilter;

    public DepthFilter(double minDepth, double maxDepth){
        minDepthFilter = minDepth;
        maxDepthFilter = maxDepth;
    }

    public boolean satisfies(QuakeEntry qe){
        if ((qe.getDepth() >= minDepthFilter) && (qe.getDepth() <= maxDepthFilter)) {
            return true;
        }
        return false;
    }
}
