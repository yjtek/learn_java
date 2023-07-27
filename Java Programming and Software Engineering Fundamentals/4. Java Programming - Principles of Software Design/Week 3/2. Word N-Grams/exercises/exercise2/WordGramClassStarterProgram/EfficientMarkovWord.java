package WordGramClassStarterProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.HashMap;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> myMap;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myMap = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
        buildMap();
	}

    public void buildMap() {
        for (int i=0; i < (myText.length-myOrder+1); i++){
            String[] nGramArray = new String[myOrder];
            System.arraycopy(myText, i, nGramArray, 0, myOrder);

            WordGram wg = new WordGram(nGramArray, 0, nGramArray.length);
            if (!myMap.containsKey(wg)) {
                myMap.put(wg, buildArrayListForHashMap(wg));
            }
            
        }
        // for (WordGram wg: myMap.keySet()) {
        //     System.out.println(wg.toString());
        // }
        printHashMapInfo();
    }

    public ArrayList<String> getFollows(WordGram key){
        return myMap.get(key);
    }
	
	public String getRandomText(int numWords){

		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with

        String[] seedWordArray = new String[myOrder];
        for (int i=0; i<seedWordArray.length; i++){
            seedWordArray[i] = myText[index+i];
        }
        WordGram key = new WordGram(seedWordArray, 0, seedWordArray.length);
        sb.append(key.toString() + " ");

		for(int k=0; k < (numWords-myOrder); k++){
            // System.out.println(k);
		    ArrayList<String> follows = getFollows(key);
		    if (follows == null || follows.size() == 0) {
		        continue;
		    }

			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next + " ");
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> buildArrayListForHashMap(WordGram kGram) {
	    // System.out.println("KGram: " + kGram);
        
        ArrayList<String> follows = new ArrayList<String>();
		int currIndex = indexOf(myText, kGram, 0);
		while (currIndex != -1 && currIndex < myText.length-myOrder) {
            // System.out.println("currIndex: " + currIndex);
			follows.add(myText[currIndex+myOrder]);
            // System.out.println("Added string: " + myText[currIndex+myOrder]);
			currIndex = indexOf(myText, kGram, currIndex+1);
            // System.out.println("new currIndex: " + currIndex);
        }
	    return follows;
    }

	private int indexOf(String[] words, WordGram target, int start) {
        
        WordGram currNGram;
        for (int i=start; i < words.length - myOrder; i++) {
            currNGram = new WordGram(Arrays.copyOfRange(words, i, i+myOrder), 0, myOrder);
            // System.out.println("currNGram: " + currNGram);
            if (currNGram.equals(target)) {
                return i;
            }
        }
        return -1;

	}

	public void testAll() {
		String testText = "this is just a test yes this is a simple test";
		String[] testTextSplit = testText.split("\s");
		// for (String s: testTextSplit){
		// 	System.out.println(s);
		// }
        
        String[] targetString = {"yes", "this", "is", "a"};
        WordGram targetWordGram = new WordGram(targetString, 0 ,targetString.length);
		System.out.println(indexOf(testTextSplit, targetWordGram, 0));
        System.out.println(getFollows(targetWordGram));

        String[] targetString2 = {"this", "is", "a", "simple"};
        targetWordGram = new WordGram(targetString2, 0 ,targetString2.length);
        System.out.println(getFollows(targetWordGram));
	}

    public void printHashMapInfo() {
        // if (myMap.size() <= 10) {
        //     for (WordGram key: myMap.keySet()){
        //         System.out.println("++++++++++++++++++++++++");
        //         System.out.println("KEY: " + key);
        //         for (String s: myMap.get(key)) {
        //             System.out.println("VALUE: " + s);
        //         }
        //     }
        // }

        System.out.println("Size of map: " + myMap.size());


        int maxArraySize = 0;
        for (WordGram key: myMap.keySet()){
            if (myMap.get(key).size() > maxArraySize) {
                maxArraySize = myMap.get(key).size();
            }
        }
        System.out.println("Size of largest array in map: " + maxArraySize);
        for (WordGram key: myMap.keySet()){
            if (myMap.get(key).size() == maxArraySize) {
                System.out.println("Key with maxArraySize of " + maxArraySize + ": " + key);
                System.out.println("Values in key with maxArraySize: ");
                for (String s: myMap.get(key)) {
                    System.out.println(s);
                }

            }
        }

    }

    public String toString() {
        return "Efficient Markov Word of order " + myOrder;
    }
}
