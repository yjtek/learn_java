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

        FileResource dictionaryResource = new FileResource();
        HashSet<String> englishWords = readDictionary(dictionaryResource);
        
        String decrypted = breakForLangauge(message, englishWords);
        
        // int[] keys = tryKeyLength(message, 4, 'e');
        // VigenereCipher vc = new VigenereCipher(keys);
        System.out.println(decrypted.substring(0, 200));

        
    }

    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> wordStrings = new HashSet<String>();
        
        for (String line: fr.lines()){
            String lowerLine = line.toLowerCase();
            wordStrings.add(lowerLine);
        }
        return wordStrings;
    }

    public int countWords(String message, HashSet<String> dictionary){
        String[] messageSplit = message.split("\\W+");
        int countWordsInDict = 0;
        for(int i=0; i<messageSplit.length; i++){
            if (dictionary.contains(messageSplit[i].toLowerCase())){
                countWordsInDict++;
            }
        }
        return countWordsInDict;
    }

    public String breakForLangauge(String encrypted, HashSet<String> dictionary){
        
        String bestDecryption = "";
        int maxWordsFound = 0;

        for(int i=1; i<=100; i++){
            int[] keys = tryKeyLength(encrypted, i, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int wordsInDecrypted = countWords(decrypted, dictionary);
            
            if (i == 38){
                System.out.println("Words found when keyLength = 38 is: " + wordsInDecrypted);
            }
            
            if (maxWordsFound < wordsInDecrypted) {
                bestDecryption = decrypted;
                maxWordsFound = wordsInDecrypted;
                System.out.println("Best keyLength is: " + i);
                System.out.println("Words found is: " + wordsInDecrypted);
            }
        }
        return bestDecryption;
    }
    
}
