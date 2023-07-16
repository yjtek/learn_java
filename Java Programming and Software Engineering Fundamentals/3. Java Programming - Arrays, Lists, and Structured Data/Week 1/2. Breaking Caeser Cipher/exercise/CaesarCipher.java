import edu.duke.*;

public class CaesarCipher {
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
