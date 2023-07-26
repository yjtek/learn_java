package InterfaceAbstractStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size, seed);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        // FileResource fr = new FileResource("romeo.txt");
		FileResource fr = new FileResource("romeo.txt");
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 615;
		
        // MarkovZero mz = new MarkovZero();
        // runModel(mz, st, size, seed);
    
        // MarkovOne mOne = new MarkovOne();
        // runModel(mOne, st, size, seed);
        
        // MarkovModel mThree = new MarkovModel(3);
        // runModel(mThree, st, size, seed);
        
        // MarkovFour mFour = new MarkovFour();
        // runModel(mFour, st, size, seed);

		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		runModel(emm, st, size, seed);

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
	
	public void testHashMap() {
		EfficientMarkovModel emm = new EfficientMarkovModel(5);
		
		// FileResource fr = new FileResource("confucius.txt");
		FileResource fr = new FileResource("romeo.txt");
		String s = fr.asString();
		s = s.replace("\n", " ");
		// s = s.replaceAll("\s+", " ");
		System.out.println(s.substring(0,200));

		emm.setTraining(s);
		// emm.printHashMapInfo();
		// printOut(emm.getRandomText(50, 615));
		
	}

	public void compareMethods() {
		MarkovModel mm = new MarkovModel(2);
		EfficientMarkovModel emm = new EfficientMarkovModel(2);

		FileResource fr = new FileResource("hawthorne.txt");
		String s = fr.asString();
		
		mm.setTraining(s);
		emm.setTraining(s);

		long time = System.nanoTime();
		for (int i=0; i<3; i++){
			printOut(mm.getRandomText(1000, 42));
		}
		long time2 = System.nanoTime();
		System.out.println("Time taken for MarkovModel: " + (time2-time));

		time = System.nanoTime();
		for (int i=0; i<3; i++){
			printOut(emm.getRandomText(1000, 42));
		}
		time2 = System.nanoTime();
		System.out.println("Time taken for EfficientMarkovModel: " + (time2-time));
	}

}
