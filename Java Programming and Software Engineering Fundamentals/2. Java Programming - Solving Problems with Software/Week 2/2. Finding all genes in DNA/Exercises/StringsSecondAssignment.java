package Exercises;

public class StringsSecondAssignment {

    public class Part1 {
        public int findStopCodon(String dna, int startIndex, String stopCodon) {
            int currIndex = dna.indexOf(stopCodon, startIndex+3);
            //System.out.println(currIndex);
            while (currIndex != -1){
                if ((currIndex-startIndex) % 3 == 0) {
                    return currIndex;
                } else {
                    currIndex = dna.indexOf(stopCodon, currIndex+3);
                }
            }
            return dna.length();
        }        

        public String findGene(String dna) {
            int atgStartIndex = dna.indexOf("ATG");
            if (atgStartIndex == -1){
                return "";
            }
            int taaStartIndex = findStopCodon(dna, atgStartIndex, "TAA");
            int tagStartIndex = findStopCodon(dna, atgStartIndex, "TAG");
            int tgaStartIndex = findStopCodon(dna, atgStartIndex, "TGA");

            int minStopIndex = Math.min(taaStartIndex, Math.min(tagStartIndex, tgaStartIndex));
            if (minStopIndex == dna.length()){
                return "";
            }
            return dna.substring(atgStartIndex, minStopIndex+3);
        }

        public void printAllGenes(String dna){
            int startIndex = 0;
            
            while (startIndex <= (dna.length() - 3)){
                String gene = findGene(dna.substring(startIndex, dna.length()));
                if (gene.length() == 0) {
                    break;
                }
                startIndex = dna.indexOf(gene) + gene.length();
                System.out.println("gene: "+gene);
            }
        }

        public void testFindStopCodon() {
            String dna = "_WTF_ATG_A_TAA_SOMETHINGAFTER";
            
            int startIndex = dna.indexOf("ATG");
            int stopCodonIndex = findStopCodon(dna, startIndex, "TAA");
            
            System.out.println("Gene is "+dna.substring(dna.indexOf("ATG"), stopCodonIndex+3));
        }

        public void testFindGene(){
            String dna = "_WTF_ATG_A_TAA_SOMETHINGAFTER";
            System.out.println(findGene(dna));
        }

        public void testPrintAllGenes(){
            String dna = "_WTF_ATG_A_TAA_SOMETHINGAFTER_ATG_B_TAA_SOMETHINGELSE";
            printAllGenes(dna);
        }
    }

    public class Part2 {
        public int howMany(String stringa, String stringb) {
            int counter = 0;
            int startIndex = 0;
            
            while (startIndex <= stringb.length()){
                int indexFound = stringb.indexOf(stringa, startIndex);
                if (indexFound == -1) {
                    break;
                }
                startIndex = indexFound + stringa.length();
                counter++;
            }
            
            return counter;
        }

        public void testHowMany(){
            String stringa = "GAA";
            String stringb = "ATGAACGAATTGAATC";
            System.out.println(howMany(stringa, stringb));

            stringa = "AA";
            stringb = "ATAAAA";
            System.out.println(howMany(stringa, stringb));
        }
    }

    public class Part3 extends Part1 {
        public int countGenes(String dna){
            int counter = 0;
            int startIndex = 0;

            while (startIndex <= (dna.length() - 3)) {
                String gene = findGene(dna.substring(startIndex, dna.length()));
                if (gene.length() == 0) {
                    break;
                }
                startIndex = dna.indexOf(gene) + gene.length();
                counter++;
            }
            return counter;
        }

        public void testCountGenes(){
            String dna = "_WTF_ATG_A_TAA_SOMETHINGAFTER_ATG_B_TAA_SOMETHINGELSE";
            System.out.println(countGenes(dna));
        }
    }

    public static void main(String[] arg){
        // StringsSecondAssignment ssa = new StringsSecondAssignment();
        // StringsSecondAssignment.Part1 p1 = ssa.new Part1();
        // p1.testFindStopCodon();
        // p1.testFindGene();
        // p1.testPrintAllGenes();

        // StringsSecondAssignment ssa = new StringsSecondAssignment();
        // StringsSecondAssignment.Part2 p2 = ssa.new Part2();
        // p2.testHowMany();

        StringsSecondAssignment ssa = new StringsSecondAssignment();
        StringsSecondAssignment.Part3 p3 = ssa.new Part3();
        p3.testCountGenes();
    }
}
