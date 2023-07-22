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

        // FileResource dictionaryResource = new FileResource();
        // HashSet<String> englishWords = readDictionary(dictionaryResource);
        HashMap<String, HashSet<String>> languageToWordHashset = makeLanguageToWordHashset();
        breakForAllLangs(message, languageToWordHashset);
        // for (String key: languageToWordHashset.keySet()){
        //     String decrypted = breakForLangauge(message, languageToWordHashset.get(key));
        // }
        
        // int[] keys = tryKeyLength(message, 4, 'e');
        // VigenereCipher vc = new VigenereCipher(keys);
        // System.out.println(decrypted.substring(0, 200));
        
    }

    public HashMap<String, HashSet<String>> makeLanguageToWordHashset() {
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languageToWordHashset = new HashMap<String, HashSet<String>>();
        for (java.io.File f: dr.selectedFiles()){
            System.out.println("Creating hashset for " + f.getName());
            FileResource fr = new FileResource(f);
            HashSet<String> hashsetWords = readDictionary(fr);
            languageToWordHashset.put(f.getName(), hashsetWords);
        }
        return languageToWordHashset;
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
        Character mostCommonChar = mostCommonCharIn(dictionary);

        for(int i=1; i<=100; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int wordsInDecrypted = countWords(decrypted, dictionary);
            
            // if (i == 38){
            //     System.out.println("Words found when keyLength = 38 is: " + wordsInDecrypted);
            // }
            
            if (maxWordsFound < wordsInDecrypted) {
                bestDecryption = decrypted;
                maxWordsFound = wordsInDecrypted;
                System.out.println("Best keyLength is: " + i);
                System.out.println("Words found is: " + wordsInDecrypted);
            }
        }
        return bestDecryption;
    }

    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<String, Integer> charCountsInLanguage = new HashMap<String, Integer>();
        for (String s: dictionary){
            for (int i=0; i<s.length(); i++) {
                Character c = s.charAt(i);
                if (Character.isAlphabetic(c)){
                    if (charCountsInLanguage.containsKey(c.toString())) {
                        int newCount = charCountsInLanguage.get(c.toString())+1;
                        charCountsInLanguage.put(c.toString(), newCount);
                    } 
                    else {
                        charCountsInLanguage.put(c.toString(), 1);
                    }
                }
            }
        }

        int maxCount = 0;
        String maxChar = "";
        for (String key: charCountsInLanguage.keySet()){
            if (charCountsInLanguage.get(key) > maxCount) {
                maxCount = charCountsInLanguage.get(key);
                maxChar = key;
            }
        }

        return maxChar.charAt(0);
    }

    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
        for (String languageDictKey: languages.keySet()){
            String decryptForLangauge = breakForLangauge(encrypted, languages.get(languageDictKey));
            System.out.println(decryptForLangauge);
        }
    }
    
}
