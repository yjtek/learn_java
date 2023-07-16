public class CaeserCypher {
    public int[] countLetters(String message) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k=0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }

    public int maxIndex(int[] vals){
        int maxDex = 0;
        for (int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }

    public String decrypt(String encrypted) {
        CaeserCypher cc = new CaeserCypher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        
        /* 
         * Assuming the maxDex found is "e", how far away from "e" is maxDex? 
         * For instance, if maxDex is at 6, "g" is most common letter, "g" is 2 away from "e"
         * So the encryption key goes forward 2 steps
         * To reverse encryption, we just take each letter and go back 2 steps
         * If maxDex is at 1, "b" most common letter, and "b" is 3 away from "e", hence we need to 
         * go back 3, or go forward 23 
        */
        int dkey = maxDex - 4; 
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }

        /*
         * Encrypting by 26-dkey is the inverse of encryption by dkey
         * i.e. Let's take 1 letter as an example
         * If we initially encrypt by 6 (go forward 6), A --> G
         * So to decrypt, we encrypt by 26-6 = 20 (go forward 20)
         * G --> A
         */
        return cc.encrypt(encrypted, 26-dkey);
    }
}

