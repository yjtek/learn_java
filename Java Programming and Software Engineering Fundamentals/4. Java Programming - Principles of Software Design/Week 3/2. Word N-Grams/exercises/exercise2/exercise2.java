import WordGramClassStarterProgram.*;

public class exercise2 {
    public static void main(String[] args){
        // String[] stringArray = {"Test1", "Test2", "Test3"};
        // WordGram wg = new WordGram(stringArray, 0, stringArray.length);
        // System.out.println(wg.toString());
        // WordGram wg2 = wg.shiftAdd("Test4");
        // System.out.println(wg.toString());
        // System.out.println(wg2.toString());

        // MarkovWord mw = new MarkovWord(4);
        // mw.setTraining("this is just a test yes this is a simple test");
        // mw.testAll();

        MarkovRunner mr = new MarkovRunner();
        // mr.runMarkov();
        mr.testHashMap();
        // mr.compareMethods();
    }
}
