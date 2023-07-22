import VigenereProgram.VigenereBreaker;
import edu.duke.*;

public class exercise {
    public static void main(String[] args){
        VigenereBreaker vb = new VigenereBreaker();
        
        // String message = "bddeggi";
        // System.out.println(vb.sliceString(message, 4, 5));
        
        FileResource fr = new FileResource("secretmessage1.txt");
        String message = fr.asString();
        int[] keyArray = vb.tryKeyLength(message, 4, 'e');
        for (int i=0; i < keyArray.length; i++){
            System.out.println(keyArray[i]);
        };

        vb.breakVigenere();
    }
}
