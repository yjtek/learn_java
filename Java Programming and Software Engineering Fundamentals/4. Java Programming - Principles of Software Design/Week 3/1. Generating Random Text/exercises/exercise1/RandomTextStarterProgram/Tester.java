package RandomTextStarterProgram;

import java.util.ArrayList;
import edu.duke.*;

public class Tester {
    public void testGetFollow(){
        MarkovOne mo = new MarkovOne();
        mo.setTraining("this is a test yes this is a test.");
        ArrayList<String> getFollowsArray = mo.getFollows("t.");
        System.out.println("Size of getFollows(): " + getFollowsArray.size());
        System.out.println("Content of getFollows(): ");
        for (String s: getFollowsArray){
            System.out.println(s);
        }
    }

    public void testGetFollowsWithFile(){
        MarkovOne mo = new MarkovOne();
        FileResource fr = new FileResource("melville.txt");
        String trainingText = fr.asString();
        mo.setTraining(trainingText);
        ArrayList<String> getFollowsArray = mo.getFollows("th");
        System.out.println("Size of getFollows(): " + getFollowsArray.size());
        // System.out.println("Content of getFollows(): ");
        // for (String s: getFollowsArray){
        //     System.out.println(s);
        // }
    }
}
