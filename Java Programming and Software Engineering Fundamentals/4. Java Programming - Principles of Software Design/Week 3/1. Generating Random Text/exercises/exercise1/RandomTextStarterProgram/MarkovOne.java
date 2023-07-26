package RandomTextStarterProgram;

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne {
    private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String currentChar = myText.substring(index, index+1);
        sb.append(currentChar);

        for (int i=0; i < numChars; i++){
            ArrayList<String> possibleChars = getFollows(currentChar);
            if (possibleChars.size() == 0) {
                break;
            }
            index = myRandom.nextInt(possibleChars.size());
            currentChar = possibleChars.get(index);
            sb.append(currentChar);
        }
        return sb.toString();

		// if (myText == null){
		// 	return "";
		// }
		// StringBuilder sb = new StringBuilder();
		// for(int k=0; k < numChars; k++){
		// 	int index = myRandom.nextInt(myText.length());
		// 	sb.append(myText.charAt(index));
		// }
		
		// return sb.toString();
	}

    public ArrayList<String> getFollows(String key){
        int nextIndex = myText.indexOf(key);
        ArrayList<String> charList = new ArrayList<String>();
        while (nextIndex != -1 && nextIndex != myText.length()-key.length()) { 
            
            String nextChar = myText.substring(nextIndex+key.length(), nextIndex+key.length()+1);
            charList.add(nextChar);

            nextIndex = myText.indexOf(key, nextIndex+key.length());
        }
        return charList;
    }
}
