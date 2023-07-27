package WordNGramStarterProgram;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
        String key2 = myText[index+1];
		sb.append(key1 + " " + key2);
		sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		    if (follows.size() == 0) {
		        break;
		    }

			// if (k < 5) {
			// 	System.out.println("=============");
			// 	System.out.println(key);
			// 	for (String s: follows) {
			// 		System.out.println(s);
			// 	}
			// }

			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			
            key1 = key2;
            key2 = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
		int currIndex = indexOf(myText, key1, key2, 0);
        
        while (currIndex != -1 && currIndex < myText.length - 2) {
            follows.add(myText[currIndex+2]);
            currIndex = indexOf(myText, key1, key2, currIndex+1);
        }
                
	    return follows;
    }

	private int indexOf(String[] words, String target1, String target2, int start) {
		
		// String[] searchSubArray = Arrays.copyOfRange(words, start, words.length);
		// for (String word: searchSubArray){
        for (int i=start; i < words.length; i++){
			if (words[i].equals(target1) && words[i+1].equals(target2)) {
				return i;
			}
		}
		return -1;
	}

	// public void testIndexOf() {
	// 	String testText = "this is just a test yes this is a simple test";
	// 	String[] testTextSplit = testText.split("\s");
	// 	// for (String s: testTextSplit){
	// 	// 	System.out.println(s);
	// 	// }
	// 	System.out.println(indexOf(testTextSplit, "this", 0));
	// 	System.out.println(indexOf(testTextSplit, "this", 3));
	// 	System.out.println(indexOf(testTextSplit, "frog", 0));
	// 	System.out.println(indexOf(testTextSplit, "frog", 5));
	// 	System.out.println(indexOf(testTextSplit, "simple", 2));
	// 	System.out.println(indexOf(testTextSplit, "test", 5));
	// }



}
