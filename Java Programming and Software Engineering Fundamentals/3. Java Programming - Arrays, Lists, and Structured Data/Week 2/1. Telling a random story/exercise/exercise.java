import java.util.ArrayList;
import edu.duke.*;

public class exercise {
    
    public class WordFrequencies {
        private ArrayList<String> myWords;
        private ArrayList<Integer> myFreqs;

        public WordFrequencies(){
            myWords = new ArrayList<String>();
            myFreqs = new ArrayList<Integer>();
        }

        public void findUnique(){
            myWords.clear();
            myFreqs.clear();

            FileResource fr = new FileResource("likeit.txt");
            for (String word: fr.words()){
                word = word.toLowerCase();
                int idx = myWords.indexOf(word);
                if (myWords.indexOf(word) == -1) {
                    myWords.add(word);
                    myFreqs.add(1);
                }
                else {
                    int value = myFreqs.get(idx);
                    myFreqs.set(idx, value+1);
                }
            }
        }

        public void tester(){
            findUnique();

            System.out.println(myWords.size());

            for (int i=0; i < myWords.size(); i++){
                System.out.println("Word: " + myWords.get(i) + " || " + "Count: " + myFreqs.get(i));
            }

            int maxIdx = findIndexOfMax();
            System.out.println("+++ Word occured most: " + myWords.get(maxIdx) + " || Count: " + myFreqs.get(maxIdx) + " +++");
        }

        public int findIndexOfMax(){
            int maxVal = 0;
            int maxIdx = 0;
            for (int i=0; i < myFreqs.size(); i++){
                if (myFreqs.get(i) > maxVal) {
                    maxVal = myFreqs.get(i);
                    maxIdx = i;
                }
            }
            return maxIdx;
        }

    }
    
    public class CharactersInPlay {
        private ArrayList<String> characterNames;
        private ArrayList<Integer> characterCounts;

        public CharactersInPlay() {
            characterNames = new ArrayList<String>();
            characterCounts = new ArrayList<Integer>();
        }

        public void update(String person){
            int idx = characterNames.indexOf(person);
            if (idx == -1) {
                characterNames.add(person);
                characterCounts.add(1);
            }
            else {
                int count = characterCounts.get(idx);
                characterCounts.set(idx, count+1);
            }
        }

        public void findAllCharacters(){
            characterNames.clear();
            characterCounts.clear();

            // FileResource fr = new FileResource("macbethSmall.txt");
            // FileResource fr = new FileResource("macbeth.txt");
            FileResource fr = new FileResource("likeit.txt");

            for (String line: fr.lines()){
                int idx = line.indexOf(".");
                if (idx != -1){
                    String characterName = line.substring(0, idx);
                    update(characterName);
                }
            }
        }

        public void tester(){
            findAllCharacters();
            characterWithNumParts(10,15);
        }

        public void characterWithNumParts(int num1, int num2){
            assert num1 <= num2;
            findAllCharacters();
            for (int i=0; i < characterNames.size(); i++){
                if (characterCounts.get(i) >= num1 && characterCounts.get(i) <= num2) {
                    System.out.println("Character Name: " + characterNames.get(i) + " || Character line count: " + characterCounts.get(i));
                }            
            }
        }
    }

    public static void main(String[] args){
        exercise e = new exercise();
        
        // WordFrequencies wf = e.new WordFrequencies();
        // wf.tester();

        CharactersInPlay cip = e.new CharactersInPlay();
        cip.tester();
    }
}
