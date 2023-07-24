import exercise1.SearchingEarthquakeDataStarterProgram.*;
import exercise1.edu.duke.*;

public class exercise1 {
    public static void main(String[] args){
        // EarthQuakeClient eqc = new EarthQuakeClient();
        // eqc.bigQuakes();
        // eqc.closeToMe();
        // eqc.quakesOfDepth();
        // eqc.quakesByPhrase();

        // ClosestQuakes cq = new ClosestQuakes();
        // cq.findClosestQuakes();

        LargestQuakes lq = new LargestQuakes();
        lq.findLargestQuakes("nov20quakedatasmall.atom");
    }
}
