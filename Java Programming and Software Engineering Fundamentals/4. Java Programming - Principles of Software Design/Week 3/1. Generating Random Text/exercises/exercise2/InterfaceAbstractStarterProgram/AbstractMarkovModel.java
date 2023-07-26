package InterfaceAbstractStarterProgram;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int mySize;
    
    public AbstractMarkovModel(int n) {
        myRandom = new Random();
        mySize = n;
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars, int seed);

    protected ArrayList<String> getFollows(String key){
        int nextIndex = myText.indexOf(key);
        ArrayList<String> charList = new ArrayList<String>();
        while (nextIndex != -1 && nextIndex != myText.length()-key.length()) { 
            
            String nextChar = myText.substring(nextIndex+key.length(), nextIndex+key.length()+1);
            charList.add(nextChar);

            nextIndex = myText.indexOf(key, nextIndex+key.length());
        }
        return charList;
    }

    public String toString() {
        return "MarkovModel of order " + mySize;
    }


}
