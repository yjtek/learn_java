package WordGramClassStarterProgram;

import java.util.Arrays;

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size); //shallow copy
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        if (myWords != null){
            return myWords.length;
        }
        return 0;
        
    }

    public String toString(){
        String ret = "";
        for (String wordgram: myWords) {
            ret += wordgram + " ";
        }

        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (myWords.length != other.length()) {
            return false;
        }

        for (int i=0; i<myWords.length; i++){
            if (!myWords[i].equals(other.wordAt(i))) {
                return false;
            }
        }

        return true;

    }

    public WordGram shiftAdd(String word) {	
        
        String[] newArray = new String[myWords.length]; 
        for (int i=0; i<myWords.length-1; i++){
            newArray[i] = new String(myWords[i+1]);
        }
        newArray[myWords.length - 1] = word;
        WordGram out = new WordGram(newArray, 0, myWords.length);
        return out;
    }

    public int hashCode() {
        String fullString = toString();
        return fullString.hashCode();
    }

}