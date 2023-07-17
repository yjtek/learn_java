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

            FileResource fr = new FileResource();
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
                System.out.println(myWords.get(i));
                System.out.println(myFreqs.get(i));
            }

            int maxIdx = findIndexOfMax();
            System.out.println(myWords.get(maxIdx) + " || " + myFreqs.get(maxIdx));
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
    
    public static void main(String[] args){
        exercise e = new exercise();
        WordFrequencies wf = e.new WordFrequencies();
        wf.tester();
    }    
}
