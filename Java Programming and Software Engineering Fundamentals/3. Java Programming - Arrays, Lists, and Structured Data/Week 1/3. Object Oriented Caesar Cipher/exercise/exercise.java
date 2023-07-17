import edu.duke.FileResource;

public class exercise {

    public class CaeserCipher {
        private String alphabet;
        private String shiftedAlphabet;
        private int mainKey;

        public CaeserCipher(int key){
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
            mainKey = key;
        }

        public String encrypt(String message){
            StringBuilder encryptedMessage = new StringBuilder(message);
            for (int i=0; i < message.length(); i++){
                char originalChar = message.charAt(i);
                if (!Character.isLetter(originalChar)) {
                    encryptedMessage.setCharAt(i, message.charAt(i));
                    continue;
                }

                if (Character.isUpperCase(originalChar)){
                    int originalCharIndex = alphabet.indexOf(originalChar);
                    char encryptedChar = shiftedAlphabet.charAt(originalCharIndex);
                    encryptedMessage.setCharAt(i, encryptedChar);
                }
                else {
                    originalChar = Character.toUpperCase(originalChar);
                    int originalCharIndex = alphabet.indexOf(originalChar);
                    char encryptedChar = shiftedAlphabet.charAt(originalCharIndex);
                    encryptedChar = Character.toLowerCase(encryptedChar);
                    encryptedMessage.setCharAt(i, encryptedChar);
                }
            }
            
            return encryptedMessage.toString();
        }

        public String decrypt(String input){
            CaeserCipher cc = new CaeserCipher(26 - mainKey);
            return cc.encrypt(input);
        }

    }

    public class TestCaeserCipher{
        private String alphabet;

        public TestCaeserCipher(){
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }

        public int[] countLetters(String message){
            
            String messageUpper = message.toUpperCase();
            
            // Create array with same length of `alphabet` to count incidence of letters
            int[] counts = new int[26];

            // Looping over all characters in message...
            for (int i=0; i < messageUpper.length(); i++){
                if (Character.isLetter(messageUpper.charAt(i))) {
                    counts[alphabet.indexOf(messageUpper.charAt(i))]++;
                }
            }

            // for (int i=0; i < counts.length; i++) {
            //     System.out.println(alphabet.charAt(i) + ": " + counts[i]);
            // }

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

        public int breakCaesarCipher(String input){
            // Count the incidence of letters in `message`
            int[] letterCounts = countLetters(input);
            
            // Assume the most frequently index is the letter "e" in reality
            int maxIndex = maxIndex(letterCounts);
            int encryptionKey = getEncryptionKey(maxIndex);
            return encryptionKey;
        }

        public String decrypt(String input){
            int key = breakCaesarCipher(input);
            CaeserCipher cc = new CaeserCipher(26-key);
            return cc.encrypt(input);
        }

        public void simpleTests(){
            // FileResource fr = new FileReource();
            // String message = fr.asString();
            
            String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";

            CaeserCipher cc = new CaeserCipher(18);
            String encryptedMessage = cc.encrypt(message);
            String decryptedMessage = cc.decrypt(encryptedMessage);
            
            System.out.println(message);
            System.out.println(encryptedMessage);
            System.out.println(decryptedMessage);
        }

    }

    public class CaesarCipherTwo {
        private String alphabet;
        private String shiftedAlphabet1;
        private String shiftedAlphabet2;
        private int enc_key1;
        private int enc_key2;

        public CaesarCipherTwo(int key1, int key2){
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
            shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
            enc_key1 = key1;
            enc_key2 = key2;
        }

        public int[] countLetters(String message){
            
            String messageUpper = message.toUpperCase();
            
            // Create array with same length of `alphabet` to count incidence of letters
            int[] counts = new int[26];

            // Looping over all characters in message...
            for (int i=0; i < messageUpper.length(); i++){
                if (Character.isLetter(messageUpper.charAt(i))) {
                    counts[alphabet.indexOf(messageUpper.charAt(i))]++;
                }
            }

            for (int i=0; i < counts.length; i++) {
                System.out.println(alphabet.charAt(i) + ": " + counts[i]);
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
    
        public String encryptTwoKeys(String input){
            StringBuilder newInput = new StringBuilder(input);
            
            CaeserCipher cc1 = new CaeserCipher(enc_key1);
            CaeserCipher cc2 = new CaeserCipher(enc_key2);

            String key1EncryptedInput = cc1.encrypt(input);
            String key2EncryptedInput = cc2.encrypt(input);
            char encChar;

            for (int i=0; i < newInput.length(); i++){
                if (i % 2 == 0){
                    encChar = key1EncryptedInput.charAt(i);
                }
                else {
                    encChar = key2EncryptedInput.charAt(i);
                }
                newInput.setCharAt(i, encChar);
            }

            return newInput.toString();
        }

        public int getKey(String input){
            // Count the incidence of letters in `message`
            int[] letterCounts = countLetters(input);
            
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

        public String decryptTwoKeys(String encrypted){
            String halfString1 = halfOfString(encrypted, 0);
            String halfString2 = halfOfString(encrypted, 1);

            int encryptionKey1 = getKey(halfString1);
            int encryptionKey2 = getKey(halfString2);

            System.out.println("Key1: " + encryptionKey1 + " || " + "Key2: " + encryptionKey2);

            CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - encryptionKey1, 26 - encryptionKey2);
            String decryptedMessage = cc2.encryptTwoKeys(encrypted);
            return decryptedMessage;
        }

        public void simpleTests(){
            // FileResource fr = new FileResource();
            // String message = fr.asString();

            String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
            CaesarCipherTwo cc2 = new CaesarCipherTwo(17, 3);
            
            String twoKeyEncryptedInput = cc2.encryptTwoKeys(message);
            System.out.println(twoKeyEncryptedInput);

            String twoKeyDecryptedInput = cc2.decryptTwoKeys(message);
            System.out.println(twoKeyDecryptedInput);
        }

    }

    public static void main(String[] args){
        exercise e = new exercise();
        // TestCaeserCipher tcc = e.new TestCaeserCipher();
        // tcc.simpleTests();

        CaesarCipherTwo cc2 = e.new CaesarCipherTwo(17, 3);
        cc2.simpleTests();
    }
}
