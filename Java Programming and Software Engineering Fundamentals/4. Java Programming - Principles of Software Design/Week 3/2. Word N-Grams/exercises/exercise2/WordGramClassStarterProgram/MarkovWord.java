package WordGramClassStarterProgram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
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

		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
		    if (follows.size() == 0) {
		        break;
		    }

			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next + " ");
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
		int currIndex = indexOf(myText, kGram, 0);
		while (currIndex != -1 && currIndex < myText.length-myOrder) {
			follows.add(myText[currIndex+myOrder]);
			currIndex = indexOf(myText, kGram, currIndex+1);
		}

	    return follows;
    }

	private int indexOf(String[] words, WordGram target, int start) {
        
        WordGram currNGram;

        for (int i=start; i < words.length; i++) {
            currNGram = new WordGram(Arrays.copyOfRange(words, i, i+myOrder), 0, myOrder);
            if (currNGram.equals(target)) {
                return i;
            }
        }
        return -1;

        // try {
        //     if (target.length() != myOrder) {
        //         throw new Exception("Requested target: `" + target + "` is not of the same order as Markov model: " + myOrder);
        //     }
        // } catch (Exception e) {
        //     System.err.println("Exception caught: " + e.getMessage());
        // }

        // return -99;
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
}
