package WordGramClassStarterProgram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource("confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        //MarkovWordOne markovWord = new MarkovWordOne(); 
        //runModel(markovWord, st, 200); 
        MarkovWord mw = new MarkovWord(5); 
        runModel(mw, st, 200, 844); 
    } 

    public void testHashMap() {
        FileResource fr = new FileResource("confucius.txt"); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "this is a test yes this is really a test";
        // String st = "this is a test yes this is really a test yes a test this is wow";
        EfficientMarkovWord emw = new EfficientMarkovWord(6); 
        runModel(emw, st, 50, 792); 
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

    public void compareMethods() {
        // MarkovWord mw = new MarkovWord(2);
        EfficientMarkovWord emw = new EfficientMarkovWord(2);

        FileResource fr = new FileResource("hawthorne.txt");
        String s = fr.asString();
        
        // long t1 = System.nanoTime();
        // System.out.println("Running MarkovWord...");
        // runModel(mw, s, 100, 42);
        // long t2 = System.nanoTime();
        // System.out.println(t2-t1);

        long t3 = System.nanoTime();
        System.out.println("Running EfficientMarkovWord...");
        runModel(emw, s, 100, 42);
        long t4 = System.nanoTime();
        System.out.println(t4-t3);        
    }

}
