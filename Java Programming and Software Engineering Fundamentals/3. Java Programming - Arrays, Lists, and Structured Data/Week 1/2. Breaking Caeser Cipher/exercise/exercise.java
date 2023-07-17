import edu.duke.*;

public class exercise {

    public class WordLengths {
        public void countWordLengths(FileResource resource, int[] counts){
            for (String s: resource.words()){
                if (s.length() == 1 && !Character.isLetter(s.charAt(0))) {
                    continue;
                }

                if (!Character.isLetter(s.charAt(0))){
                    s = s.substring(1);
                } 
                if (!Character.isLetter(s.charAt(s.length()-1))){
                    s = s.substring(0, s.length()-1);
                }

                if (s.length() >= counts.length){
                    counts[counts.length]++;
                } 
                else {
                    counts[s.length()]++;
                }
            }

            for (int i=0; i < counts.length; i++){
                System.out.print("i: "+ i + " || " + "counts: " + counts[i] + "\n");
            }
            // return counts;
        }

        public void testCountWordLengths(){
            FileResource fr = new FileResource("lotsOfWords.txt");
            int[] counts = new int[50];
            countWordLengths(fr, counts);
        }
    }

    public class CaesarBreaker {

        public String decrypt(String message){
            int encryptionKey = getKey(message);
            System.out.println("Key found: " + encryptionKey);
            
            CaesarCipher cc = new CaesarCipher();
            String decryptedMessage = cc.encrypt(message, 26 - encryptionKey);
            return decryptedMessage;
        }

        public int getKey(String message){
            // Count the incidence of letters in `message`
            int[] letterCounts = countLetters(message);
            
            // Assume the most frequently index is the letter "e" in reality
            int maxIndex = maxIndex(letterCounts);
            int encryptionKey = getEncryptionKey(maxIndex);
            return encryptionKey;
        }

        public int getEncryptionKey(int maxIndex) {
            
            // "e" is index 4 in the alphabet (based on 0 indexing). 
            // So the distance of maxIndex from "e" is key used in the encryption (i.e. number of
            // forward offsets for each letter)
            int encryptionKey = maxIndex - 4;

            // It is possible that maxIndex smaller than 4, this just means that the offset is 
            // so large that it looped around after "z"
            if (encryptionKey < 0){
                encryptionKey = 26 - 4 + maxIndex;
            }
            return encryptionKey;
        }

        public int[] countLetters(String message){
            
            String messageLower = message.toLowerCase();
            // Create a string of all letters to check index
            String alphabets = "abcdefghijklmnopqrstuvwxyz";

            // Create array with same length of `alphabets` to count incidence of letters
            int[] counts = new int[26];

            // Looping over all characters in message...
            for (int i=0; i < messageLower.length(); i++){
                if (Character.isLetter(messageLower.charAt(i))) {
                    counts[alphabets.indexOf(messageLower.charAt(i))]++;
                }
            }

            for (int i=0; i < counts.length; i++) {
                System.out.println(alphabets.charAt(i) + ": " + counts[i]);
            }

            return counts;
        }
    
        public int maxIndex(int[] letterCounts) {
            int maxCount = 0;
            int maxIndex = 0;
            for (int i=0; i < letterCounts.length; i++){
                if (letterCounts[i] > maxCount) {
                    maxCount = letterCounts[i];
                    maxIndex = i;
                }
            }
            return maxIndex;
        }
    
        public String halfOfString(String message, int start){
            
            String halfString = "";
            
            if (start == 1){
                message = message.substring(1);
            }
            for (int i=0; i < message.length(); i++){
                if (i % 2 == 0){
                    halfString = halfString + message.charAt(i);
                }
            }
            return halfString;
        }

        public String decryptTwoKeys(String encrypted){
            String halfString1 = halfOfString(encrypted, 0);
            String halfString2 = halfOfString(encrypted, 1);

            int encryptionKey1 = getKey(halfString1);
            int encryptionKey2 = getKey(halfString2);

            System.out.println("Key1: " + encryptionKey1 + " || " + "Key2: " + encryptionKey2);

            CaesarCipher cc = new CaesarCipher();
            String decryptedMessage = cc.encryptTwoKeys(encrypted, 26 - encryptionKey1, 26 - encryptionKey2);
            return decryptedMessage;
        }

        public void testHalfOfString(){
            String msg = "0123456789";
            System.out.println(halfOfString(msg, 0));
            System.out.println(halfOfString(msg, 1));
        }
    }

    public class Simple{ 
        private String word; 
        private String phrase; 
        public Simple(int number, String w) { 	
             word = w; 	
             phrase = mystery(number, w);          
        }   
        private String mystery(int num, String s) {  	
             String answer = "";  	
             for (int k=0; k<num; k++) {     	
                  answer = answer + s;  	
             }  	
             return answer; 
        } 
   
        public String toString() { 	
             return phrase + " is " + word + " repeated";
        }
    }
    
    public class TestSimple {
        public void print() {  	
            Simple item = new Simple(3, "blue");     	
            // System.out.println(String.valueOf(item));
            System.out.println(item);
            System.out.println(item.mystery(5, "ho"));
        }
    }

    public static void main(String[] args){
        exercise e = new exercise();
        // WordLengths wl = e.new WordLengths();
        // wl.testCountWordLengths();

        // CaesarCipher cc = new CaesarCipher();
        // String encryptedMessage = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?", 15);
        // String encryptedMessage = cc.encryptTwoKeys("Can you imagine life WITHOUT the internet AND computers in your pocket?", 21, 8);
        // System.out.println(encryptedMessage);
        
        // CaesarBreaker cb = e.new CaesarBreaker();
        // System.out.println(cb.decrypt(encryptedMessage));
        
        // CaesarBreaker cb = e.new CaesarBreaker();
        // cb.testHalfOfString();

        // CaesarBreaker cb = e.new CaesarBreaker();
        // System.out.println(cb.decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        // System.out.println(cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        // FileResource fr = new FileResource("mysteryTwoKeysPractice.txt");
        // String message = fr.asString();
        // System.out.println(cb.decryptTwoKeys(message));

        // CaesarCipher cc = new CaesarCipher();
        // System.out.println(cc.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26-2, 26-20));

        // WordLengths wl = e.new WordLengths();
        // FileResource fr = new FileResource("manywords.txt");
        // int[] counts = new int[50];
        // wl.countWordLengths(fr, counts);

        // CaesarCipher cc = new CaesarCipher();
        // System.out.println(cc.encryptTwoKeys("Hfs cpwewloj loks cj Hoto kyg Cyy.", 12, 2));

        // CaesarBreaker cb = e.new CaesarBreaker();
        // System.out.println(cb.decryptTwoKeys("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"));

        // CaesarBreaker cb = e.new CaesarBreaker();
        // FileResource fr = new FileResource("mysteryTwoKeysQuiz.txt");
        // String message = fr.asString();
        // System.out.println(cb.decryptTwoKeys(message));

        TestSimple ts = e.new TestSimple();
        ts.print();
    }
}
