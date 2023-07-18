import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;

import edu.duke.*;

public class exercise2 {
    public class CodonCount {
        private HashMap<String, Integer> codonCounts;

        public CodonCount() {
            codonCounts = new HashMap<String, Integer>();
        }

        public boolean checkMapIsNullOrEmpty(HashMap<?, ?> map){
            return map == null || map.isEmpty();
        }

        public void buildCodonMap(int start, String dna){
            if (!checkMapIsNullOrEmpty(codonCounts)){
                codonCounts.clear();
            }
            
            String dnaFrame = dna.substring(start);
            for (int i = 0; i+3 < dnaFrame.length(); i+=3){
                String codon = dnaFrame.substring(i,i+3);
                if (codonCounts.containsKey(codon)) {
                    int val = codonCounts.get(codon);
                    codonCounts.put(codon, val+1);
                }
                else {
                    codonCounts.put(codon, 1);
                }
            }
        } 

        public String getMostCommonCodon(){
            int maxCount = 0;
            String mostCommonCodon = "";
            for (String key: codonCounts.keySet()) {
                int count = codonCounts.get(key);
                if (count > maxCount){
                    maxCount = count;
                    mostCommonCodon = key;
                }
            }
            return mostCommonCodon;
        }

        public void printCodonCounts(int start, int end){
            for (String key: codonCounts.keySet()){
                int count = codonCounts.get(key);
                if (count >= start && count <= end) {
                    System.out.println("Codon: " + key + " || Count: " + codonCounts.get(key));
                }
            }
        }

        public void tester(){
            FileResource fr = new FileResource();
            String dna = fr.asString();
            
            buildCodonMap(0, dna);
            System.out.println("Count unique codons: " + codonCounts.size());
            System.out.println("Most common codon: " + getMostCommonCodon() + " || Count: " + codonCounts.get(getMostCommonCodon()));
            printCodonCounts(0,5);

            buildCodonMap(1, dna);
            System.out.println("Count unique codons: " + codonCounts.size());
            System.out.println("Most common codon: " + getMostCommonCodon() + " || Count: " + codonCounts.get(getMostCommonCodon()));
            printCodonCounts(0,5);

            buildCodonMap(2, dna);
            System.out.println("Count unique codons: " + codonCounts.size());
            System.out.println("Most common codon: " + getMostCommonCodon() + " || Count: " + codonCounts.get(getMostCommonCodon()));
            printCodonCounts(0,5);
        }
    }

    public class WordsinFiles {
        private HashMap<String, ArrayList<String>> mapWordToFiles;
        
        public WordsinFiles() {
            mapWordToFiles = new HashMap<String, ArrayList<String>>();
        }
        
        private void addWordsFromFile(File f) {
            FileResource fr = new FileResource(f.toString());
            String name = f.getName();

            for (String word: fr.words()){
                if (!mapWordToFiles.containsKey(word)){
                    mapWordToFiles.put(word, new ArrayList<String>());
                    mapWordToFiles.get(word).add(name);
                }
                else {
                    if (!mapWordToFiles.get(word).contains(name)){
                        mapWordToFiles.get(word).add(name);
                    }
                }
            }
        }

        public void buildWordFileMap(){
            mapWordToFiles.clear();
            DirectoryResource dr = new DirectoryResource();
            for (File f: dr.selectedFiles()){
                addWordsFromFile(f);
            }
        }

        public int maxNumber(){
            int maxCount = 0;
            for (String key: mapWordToFiles.keySet()){
                int count = mapWordToFiles.get(key).size();
                if (count > maxCount){
                    maxCount = count;
                }
            }
            return maxCount;
        }
        
        public ArrayList<String> wordsInNumFiles(int number){
            ArrayList<String> arrayWordsInNumFiles = new ArrayList<String>();
            for (String key: mapWordToFiles.keySet()){
                if (mapWordToFiles.get(key).size() == number){
                    arrayWordsInNumFiles.add(key);
                }
            }
            return arrayWordsInNumFiles;
        }

        public void printFilesIn(String word){
            ArrayList<String> wordInFiles = mapWordToFiles.get(word);
            for (int i=0; i<wordInFiles.size(); i++){
                System.out.println(wordInFiles.get(i));
            }
        }

        public void printFullMap(){
            if (mapWordToFiles.size() <= 100) {
                for (String key: mapWordToFiles.keySet()){
                    System.out.println("Key: " + key.toString());
                    System.out.println("Value: " + mapWordToFiles.get(key).toString());
                }
            }
        }

        public void tester(){
            buildWordFileMap();
            System.out.println("Maximum number of files a word is in: " + maxNumber());
            // System.out.println("All words with max number of occurences in files: " + wordsInNumFiles(maxNumber()));
            for (String word: wordsInNumFiles(maxNumber())){
                System.out.println("Word: " + word);
                System.out.println("Word in files: ");
                printFilesIn(word);
            }
            printFullMap();
        }
    }
    
    public static void main(String[] args){
        // exercise2 e2 = new exercise2();
        
        // CodonCount cc = e2.new CodonCount();
        // cc.tester();        

        // WordsinFiles wif = e2.new WordsinFiles();
        // wif.tester();

        GladLibMap glm = new GladLibMap("data");
        glm.makeStory();
    }

    
}
