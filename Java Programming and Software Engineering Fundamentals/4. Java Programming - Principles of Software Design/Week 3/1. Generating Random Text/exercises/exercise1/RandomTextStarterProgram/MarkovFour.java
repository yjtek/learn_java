package RandomTextStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {
    private String myText;
	private Random myRandom;
	
	public MarkovFour() {
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
        int index = myRandom.nextInt(myText.length() - 4);
        String currentKey = myText.substring(index, index+4);
        sb.append(currentKey);

        for (int i=0; i < numChars-4; i++){
            ArrayList<String> possibleChars = getFollows(currentKey);
            if (possibleChars.size() == 0) {
                break;
            }
            index = myRandom.nextInt(possibleChars.size());
            String nextChar = possibleChars.get(index);
            sb.append(nextChar);
            currentKey = currentKey.substring(1) + nextChar;
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
