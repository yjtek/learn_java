package InterfaceAbstractStarterProgram;

import java.util.Random;
import java.util.ArrayList;

public class MarkovOne extends AbstractMarkovModel {
	
	public MarkovOne() {
		super(1);
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
}
