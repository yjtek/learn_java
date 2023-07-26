package InterfaceAbstractStarterProgram;

import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;
import java.util.HashSet;

public class EfficientMarkovModel extends AbstractMarkovModel {
    private HashMap<String, ArrayList<String>> map;

    public EfficientMarkovModel(int n) {
        super(n);
		map = new HashMap<String, ArrayList<String>>();
		// map = buildMap();
    }
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s){
		myText = s.trim();
		map = buildMap();
		printHashMapInfo();
		// System.out.println(myText);
	}
	
	public String getRandomText(int numChars, int seed){
		if (myText == null){
			return "";
		}
        myRandom = new Random(seed);
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - mySize);
        String currentKey = myText.substring(index, index+mySize);
        sb.append(currentKey);

        for (int i=0; i < numChars-mySize; i++){
            ArrayList<String> possibleChars = getFollows(currentKey);
            if (possibleChars == null || possibleChars.size() == 0) {
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

	public HashMap<String, ArrayList<String>> buildMap() {
		HashSet<String> allKeys = new HashSet<String>();
		for (int i=0; i<(myText.length()-mySize+1); i++){
			String key = myText.substring(i, i+mySize);
			if (!allKeys.contains(key)) {
				allKeys.add(key);
			}
			// System.out.println("Done until: " + i);
		}

		HashMap<String, ArrayList<String>> mapKeyToNextChar = new HashMap<String, ArrayList<String>>();
		for (String key: allKeys) {
			ArrayList<String> nextCharList = new ArrayList<String>();
			int currIndex = myText.indexOf(key);
			while (currIndex != -1 && currIndex+mySize < myText.length()) {
				String nextChar = myText.substring(currIndex+mySize, currIndex+mySize+1);
				nextCharList.add(nextChar);
				// currIndex = myText.indexOf(key, currIndex+mySize+1);
				currIndex = myText.indexOf(key, currIndex+1);
			}
			mapKeyToNextChar.put(key, nextCharList);
		} 

		return mapKeyToNextChar;
	}

	public String toString() {
		return "EfficientMarkovModel of class " + mySize;
	}

	public ArrayList<String> getFollows(String key) {
		return map.get(key);
	}

	public void printHashMapInfo() {
		if (map.keySet().size() < 1) {
			for (String key: map.keySet()) {
				System.out.println("+++++++++++++++++++");
				System.out.println(key);
				for (String s: map.get(key)) {
					System.out.println(s);
				}
			}	
		}

		// for (String s: map.get("Julie")) {
		// 	System.out.println(s);
		// }

		// for (String key: map.keySet()) {
		// 	if (key.length() != mySize) {
		// 		System.out.println("Key is smaller than mySize: " + key);
		// 	}
		// }

		// for (int i=0; i<10; i++) {
		// 	for (String key: map.keySet()) {
		// 		System.out.println("+++++++++++++++++++");
		// 		System.out.println(key);
		// 		for (String s: map.get(key)) {
		// 			System.out.println(s);
		// 		}
		// 	}
		// }

		System.out.println("Number of keys found in map: " + map.keySet().size());

		int maxArraySize = 0;
		String maxArrayKey = "";
		for (String key: map.keySet()) {
			if (map.get(key).size() > maxArraySize) {
				maxArraySize = map.get(key).size();
				maxArrayKey = key;
				// System.out.println("Max size found on key: " + key);
				// System.out.println("Max size is: " + map.get(key).size());
			}
		}
		System.out.println("Max array size is: " + maxArraySize);

		for (String key: map.keySet()) {
			// System.out.println(map.get(key).size());
			if (map.get(key).size() == maxArraySize) {
				System.out.println("Key with maxArraySize: " + key);
			}
		}
	}
}
