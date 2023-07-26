package InterfaceAbstractStarterProgram;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour extends AbstractMarkovModel {
	
	public MarkovFour() {
		super(4);
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	
	public String getRandomText(int numChars, int seed){
		if (myText == null){
			return "";
		}
        myRandom = new Random(seed);
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

}
