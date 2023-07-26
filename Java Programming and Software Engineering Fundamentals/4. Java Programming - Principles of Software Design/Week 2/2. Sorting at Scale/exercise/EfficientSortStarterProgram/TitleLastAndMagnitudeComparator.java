package EfficientSortStarterProgram;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String t1 = q1.getInfo();
        String t2 = q2.getInfo();

        String t1Last= t1.split("\\s")[t1.split("\\s").length - 1];
        String t2Last= t2.split("\\s")[t2.split("\\s").length - 1];

        if (t1Last.compareTo(t2Last) < 0) {
            return -1;
        } 
        else if (t1Last.compareTo(t2Last) > 0) {
            return 1;
        }
        else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
