import java.util.HashSet;

import VigenereProgram.VigenereBreaker;
import edu.duke.*;

public class exercise {
    
    public static void main(String[] args){
        VigenereBreaker vb = new VigenereBreaker();
        // FileResource fr = new FileResource("English");
        // HashSet<String> wordsInDict = vb.readDictionary(fr);
        // System.out.println(vb.mostCommonCharIn(wordsInDict));
        vb.breakVigenere();
    }
    
}
