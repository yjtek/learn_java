import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            System.out.println(currDist);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        Iterable<Point> all_points = s.getPoints();
        int counter = 0;
        for (Point p : all_points){
            counter++;
        }
        return counter;
    }

    public double getAverageLength(Shape s) {
        double lengths = getPerimeter(s);
        int points = getNumPoints(s);
        double avgLength = lengths/points;

        return avgLength;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;
        for (Point currPt : s.getPoints()) {
            if (currPt.getX() > largestX){
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        
        double max_perim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double perimeter = getPerimeter(s);
            if (perimeter > max_perim){
                max_perim = perimeter;
            }
        }
        return max_perim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        
        double max_perim = 0.0;
        String max_perim_file = null;

        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            
            double perimeter = getPerimeter(s);
            if (perimeter > max_perim){
                max_perim = perimeter;
                max_perim_file = f.getName();
            }
        }
        return max_perim_file;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        double numpoints = getNumPoints(s);
        System.out.println("num points = " + numpoints);

        double avgLength = getAverageLength(s);
        System.out.println("avgLength = " + avgLength);

        double largestSide = getLargestSide(s);
        System.out.println("largestSide = " + largestSide);

        double largestX = getLargestX(s);
        System.out.println("largestX = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        double max_perim = pr.getLargestPerimeterMultipleFiles();
        System.out.println(max_perim);
    }

    public void testFileWithLargestPerimeter() {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        String max_perim_file = pr.getFileWithLargestPerimeter();
        System.out.println(max_perim_file);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(4,0));
        triangle.addPoint(new Point(0,3));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);

        int numPoints = getNumPoints(triangle);
        System.out.println("numPoints = "+numPoints);

        double avgLength = getAverageLength(triangle);
        System.out.println("avgLength = "+avgLength);

        double largestSide = getLargestSide(triangle);
        System.out.println("largestSide = "+largestSide);

        double largestX = getLargestX(triangle);
        System.out.println("largestX = "+largestX);

    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        // pr.testFileWithLargestPerimeter();
        // pr.triangle();
    }
}
