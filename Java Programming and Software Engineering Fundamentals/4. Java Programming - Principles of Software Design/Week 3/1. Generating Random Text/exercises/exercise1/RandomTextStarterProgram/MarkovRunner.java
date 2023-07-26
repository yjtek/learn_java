package RandomTextStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*;

public class MarkovRunner {
	private String mySource;

	public MarkovRunner(String source) {
		mySource = source;
	}

    public void runMarkovZero() {
		FileResource fr = new FileResource(mySource);
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovZero markov = new MarkovZero();
		markov.setRandom(88);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

    public void runMarkovOne() {
		FileResource fr = new FileResource(mySource);
		String st = fr.asString();
		st = st.replace('\n', ' ');
		MarkovOne markov = new MarkovOne();
		markov.setRandom(273);
		markov.setTraining(st);
		for(int k=0; k < 3; k++){
			String text = markov.getRandomText(500);
			printOut(text);
		}
	}

	public void runMarkovFour(){
		FileResource fr = new FileResource(mySource);
		String s = fr.asString();
		s = s.replace("\n", " ");
		MarkovFour m4 = new MarkovFour();
		m4.setRandom(371);
		m4.setTraining(s);
		for (int i=0; i<3; i++){
			String text = m4.getRandomText(500);
			printOut(text);
		}

	}

	public void runMarkovModel(){
		FileResource fr = new FileResource(mySource);
		String s = fr.asString();
		s = s.replace("\n", " ");
		MarkovModel m4 = new MarkovModel(8);
		m4.setRandom(365);
		m4.setTraining(s);
		for (int i=0; i<3; i++){
			String text = m4.getRandomText(500);
			printOut(text);
		}

	}
	
	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
}
