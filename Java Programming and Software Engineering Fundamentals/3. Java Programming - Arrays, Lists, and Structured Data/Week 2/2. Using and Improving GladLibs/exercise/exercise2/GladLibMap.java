import edu.duke.*;
import java.util.*;

public class GladLibMap {
		
	private Random myRandom;
	private String mySource;
	private int countWordSubstituted;
	private ArrayList<String> wordsUsed;
	private ArrayList<String> categoriesUsed;

	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";

	private HashMap<String, ArrayList<String>> myMap;

	// public GladLibMap(){
	// 	initializeFromSource(dataSourceDirectory);
	// 	myRandom = new Random();
	// }
	
	public GladLibMap(String source){
		myMap = new HashMap<String, ArrayList<String>>();
		initializeFromSource(source);
		myRandom = new Random();
		
		mySource = source;
		wordsUsed = new ArrayList<String>();
		categoriesUsed = new ArrayList<String>();
		countWordSubstituted = 0;
	}
	
	private void initializeFromSource(String source) {
		ArrayList<String> wordLists = new ArrayList<String>(Arrays.asList(
			"adjective", "noun", "color", "country", "name", "animal", "timeframe", 
			"verb", "fruit"
		));

		for (int i=0; i < wordLists.size(); i++){
			myMap.put(wordLists.get(i), readIt(source + "/" + wordLists.get(i) + ".txt"));
		}
	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label == "number") {
			return ""+myRandom.nextInt(50)+5;
		}
		else if (myMap.containsKey(label)) {
			return randomFrom(myMap.get(label));
		}
		else {
			return "**UNKNOWN**";
		}
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
			if (!categoriesUsed.contains(w.substring(first+1, last))){
				categoriesUsed.add(w.substring(first+1, last));
			}
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
	
	public int totalWordsInMap(){
		int totalWords = 0;
		for (String key: myMap.keySet()){
			totalWords += myMap.get(key).size();
		}
		return totalWords;
	}

	public int totalWordsConsidered(){
		int totalWordsConsidered = 0;
		for (int i=0; i < categoriesUsed.size(); i++){
			totalWordsConsidered += myMap.get(categoriesUsed.get(i)).size();
		}
		return totalWordsConsidered;
	}

}
