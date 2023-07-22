package VigenereProgram;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sbMessage = new StringBuilder();
    
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            sbMessage.append(message.charAt(i));
        }
        
        return sbMessage.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i<klength; i++){
            String stringSlice = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(stringSlice);    
        }
        return key;
    }

    public void breakVigenere () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int[] keys = tryKeyLength(message, 4, 'e');
        VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(vc.decrypt(message).substring(0, 200));
    }
    
}
