package exercise2;

import edu.duke.*;
import java.util.*;

public class GladLibMap {
		
	private Random myRandom;
	private String mySource;
	private int countWordSubstituted;
	private ArrayList<String> wordsUsed;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";

	// YJ Addition:
	// private ArrayList<String> adjectiveList;
	// private ArrayList<String> nounList;
	// private ArrayList<String> colorList;
	// private ArrayList<String> countryList;
	// private ArrayList<String> nameList;
	// private ArrayList<String> animalList;
	// private ArrayList<String> timeList;	
	// private ArrayList<String> verbList;
	// private ArrayList<String> fruitList;
	private HashMap<String, ArrayList<String>> myMap;
	//

	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
		// YJ Addition:
		mySource = source;
		wordsUsed = new ArrayList<String>();
		countWordSubstituted = 0;
		myMap = new HashMap<String, ArrayList<String>>();
		//
	}
	
	private void initializeFromSource(String source) {
		// adjectiveList= readIt(source+"/adjective.txt");	
		// nounList = readIt(source+"/noun.txt");
		// colorList = readIt(source+"/color.txt");
		// countryList = readIt(source+"/country.txt");
		// nameList = readIt(source+"/name.txt");		
		// animalList = readIt(source+"/animal.txt");
		// timeList = readIt(source+"/timeframe.txt");		

		// // YJ Addition:
		// verbList = readIt(source+"/verb.txt");
		// fruitList = readIt(source+"/fruit.txt");
		// //
		

	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}

		// YJ Addition:
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		//

		return "**UNKNOWN**";
	}
	
	private String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		
		String sub = null;
		int i = 0;
		while (sub == null && i <= 100){
			sub = getSubstitute(w.substring(first+1,last));
			if (wordsUsed.indexOf(sub) != -1){
				sub = null;
			} 
			else {
				wordsUsed.add(sub);
			}
		}

		if (i == 100 && sub==null){
			return "**ALL WORDS USED**";
		} else {
			countWordSubstituted++;
			return prefix+sub+suffix;
		}
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}

		System.out.println("\n Count of words substituted: "+countWordSubstituted);
	}
	
	private String fromTemplate(String source){
		String story = "";
		wordsUsed.clear();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	public void makeStory(){
	    System.out.println("\n");
		
		// YJ modification
		// String story = fromTemplate(mySource + "/madtemplate.txt");
		String story = fromTemplate(mySource + "/madtemplate2.txt");
		//

		printOut(story, 60);
	}
	


}
