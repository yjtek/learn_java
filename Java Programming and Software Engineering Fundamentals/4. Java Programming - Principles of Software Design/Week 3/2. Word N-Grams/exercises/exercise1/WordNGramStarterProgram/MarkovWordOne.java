package WordNGramStarterProgram;
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
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
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
		int currIndex = indexOf(myText, key, 0);
		while (currIndex != -1 && currIndex != myText.length-1) {
			follows.add(myText[currIndex+1]);
			currIndex = indexOf(myText, key, currIndex+1);
		}

	    return follows;
    }

	private int indexOf(String[] words, String target, int start) {
		int index = 0;
		String[] searchSubArray = Arrays.copyOfRange(words, start, words.length);
		for (String word: searchSubArray){
			// System.out.println("`" + word + "`" + " || " + "`" + target + "`" + " || ");
			// System.out.println(target);
			if (word.equals(target)) {
				return index+start;
			}
			index++;
		}
		return -1;
	}

	public void testIndexOf() {
		String testText = "this is just a test yes this is a simple test";
		String[] testTextSplit = testText.split("\s");
		// for (String s: testTextSplit){
		// 	System.out.println(s);
		// }
		System.out.println(indexOf(testTextSplit, "this", 0));
		System.out.println(indexOf(testTextSplit, "this", 3));
		System.out.println(indexOf(testTextSplit, "frog", 0));
		System.out.println(indexOf(testTextSplit, "frog", 5));
		System.out.println(indexOf(testTextSplit, "simple", 2));
		System.out.println(indexOf(testTextSplit, "test", 5));
	}

}
