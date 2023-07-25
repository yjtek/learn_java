package exercise2;

import exercise2.EarthquakeFilterStarterProgram.EarthQuakeClient2;
import exercise2.EarthquakeFilterStarterProgram.LargestQuakes;

public class exercise {
    public static void main(String[] args){
        EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
        // eqc2.testMatchAllFilter();
        // eqc2.testMatchAllFilter2();
        eqc2.quakesWithFilter();

        // LargestQuakes lq = new LargestQuakes();
        // lq.findLargestQuakes("nov20quakedata.atom");
    }
}
