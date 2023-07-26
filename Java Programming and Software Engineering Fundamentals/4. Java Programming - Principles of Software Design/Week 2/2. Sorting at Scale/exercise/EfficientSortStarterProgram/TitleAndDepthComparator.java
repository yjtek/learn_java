package EfficientSortStarterProgram;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String t1 = q1.getInfo();
        String t2 = q2.getInfo();

        if (t1.compareTo(t2) < 0) {
            return -1;
        }
        else if (t1.compareTo(t2) > 0) {
            return 1;
        }
        else {
            return Double.compare(q1.getDepth(), q2.getDepth());   
        }
    }
}
