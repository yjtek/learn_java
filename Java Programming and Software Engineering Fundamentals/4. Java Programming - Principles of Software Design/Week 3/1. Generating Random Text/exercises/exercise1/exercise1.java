import RandomTextStarterProgram.*;

public class exercise1 {
    public static void main(String[] args){
        MarkovRunner mr = new MarkovRunner("confucius.txt");
        // mr.runMarkovZero();
        // mr.runMarkovOne();
        // mr.runMarkovFour();
        mr.runMarkovModel();

        // Tester t = new Tester();
        // t.testGetFollow();
        // t.testGetFollowsWithFile();
    }
}
