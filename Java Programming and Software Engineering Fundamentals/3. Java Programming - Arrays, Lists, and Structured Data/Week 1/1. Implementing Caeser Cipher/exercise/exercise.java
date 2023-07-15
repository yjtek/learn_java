import edu.duke.FileResource;

public class exercise {
    public class WordPlay {
        public boolean isVowel(char ch){
            String VOWELS = "aeiouAEIOU";
            
            if (VOWELS.indexOf(ch) != -1) {
                return true;
            }
            else {
                return false;
            }
        }

        public String replaceVowels(String phrase, char ch){
            StringBuilder newPhrase = new StringBuilder(phrase);
            for (Integer i = 0; i < newPhrase.length(); i++){
                char currentCharacter = newPhrase.charAt(i);
                if (isVowel(currentCharacter)) {
                    newPhrase.setCharAt(i, ch);
                }
            }
            return newPhrase.toString();
        }

        public String emphasize(String phrase, Character ch){
            StringBuilder newPhrase = new StringBuilder(phrase);
            for (Integer i=0; i < newPhrase.length(); i++){
                if (
                    Character.valueOf(Character.toUpperCase(newPhrase.charAt(i))).equals(ch) ||
                    Character.valueOf(Character.toLowerCase(newPhrase.charAt(i))).equals(ch)
                ){
                    if ((i % 2) == 1){
                        newPhrase.setCharAt(i, '+');
                    }
                    else {
                        newPhrase.setCharAt(i, '*');
                    }
                }
            }
            return newPhrase.toString();
        }

        public void testReplaceVowels(){
            System.out.println(replaceVowels("Hello World", '*'));
        }
        
        public void testEmphasize(){
            System.out.println(emphasize("dna ctgaaactga", 'a'));
            System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
        }

    }

    public class CaeserCipher {
        public String encrypt(String input, int key){
            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
            String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

            StringBuilder newInput = new StringBuilder(input);
            for (int i=0; i < newInput.length(); i++){
                char character = newInput.charAt(i);
                
                if (alphabet.indexOf(character) == -1 && alphabetLower.indexOf(character) == -1) {
                    continue;
                };

                if (Character.isLowerCase(character)) {
                    character = Character.toUpperCase(character);
                    int idx = alphabet.indexOf(character);
                    char newCharacter = Character.toLowerCase(shiftedAlphabet.charAt(idx));
                    newInput.setCharAt(i, newCharacter);
                }
                else {
                    int idx = alphabet.indexOf(character);
                    char newCharacter = shiftedAlphabet.charAt(idx);
                    newInput.setCharAt(i, newCharacter);
                }
            }
            return newInput.toString();
        }

        public String encryptTwoKeys(String input, int key1, int key2){
            StringBuilder newInput = new StringBuilder(input);
            String key1_enc = encrypt(input, key1);
            String key2_enc = encrypt(input, key2);
            char encChar;

            for (int i=0; i < newInput.length(); i++){
                if (i % 2 == 0){
                    encChar = key1_enc.charAt(i);
                }
                else {
                    encChar = key2_enc.charAt(i);
                }
                newInput.setCharAt(i, encChar);
            }

            return newInput.toString();
        }

        public void testCaeser(){
            // FileResource fr = new FileResource();
            // String message = fr.asString();
            
            // String message = "FIRST LEGION ATTACK EAST FLANK!";
            // String encrypted = encrypt(message, 23);
            // System.out.println("key is " + 23 + "\n" + encrypted);

            // message = "First Legion Attack East Flank!";
            // encrypted = encrypt(message, 23);
            // System.out.println("key is " + 23 + "\n" + encrypted);

            // message = "First Legion";
            // encrypted = encrypt(message, 23);
            // System.out.println("key is " + 23 + "\n" + encrypted);

            // message = "First Legion";
            // encrypted = encrypt(message, 17);
            // System.out.println("key is " + 17 + "\n" + encrypted);

            // message = "First Legion";
            // String encryptedTwoKeys = encryptTwoKeys(message, 23, 17);
            // System.out.println("key is " + 23 + " and " + 17 + "\n" + encryptedTwoKeys);

            String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
            String encrypted = encrypt(message, 15);
            System.out.println("key is " + 15 + "\n" + encrypted);

            String encryptedTwoKeys = encryptTwoKeys(message, 8, 21);
            System.out.println("key is " + 8 + " and " + 21 + "\n" + encryptedTwoKeys);

        }
    }

    public static void main(String[] args){
        exercise e = new exercise();
        // WordPlay w = e.new WordPlay();
        // w.testReplaceVowels();
        // w.testEmphasize();

        CaeserCipher c = e.new CaeserCipher();
        c.testCaeser();
    }

}
