package Exercise1;

import edu.duke.*;

public class StringsFirstAssignments {
    public class Part1 {
        public String findSimpleGene(String dna){
            String gene = "";
            int startIndex = dna.indexOf("ATG");
            int stopIndex = dna.indexOf("TAA", startIndex+3);
            if(startIndex == -1 || stopIndex == -1){
                return "";
            }

            gene = dna.substring(startIndex, stopIndex+3);
            if(gene.length() % 3 != 0){
                return "";
            }

            return gene;
        }

        public void testFindSimpleGene(){
            String dna = "ATG_VALIDGENE1_TAA";
            String gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_VALIDGENE1_TAA_INVALIDBIT";
            gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "atg_validgene1_taa_invalidbit";
            gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "MISSINGSTART_VALIDGENE1_TAA";
            gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_VALIDGENE1_MISSINGEND"; 
            gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_INVALIDGENE_TAA";
            gene = findSimpleGene(dna);
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);
        }
    }

    public class Part2 {
        public String findSimpleGene(String dna, String startCodon, String stopCodon){
            String gene = "";

            String dnaLower = dna.toLowerCase();
            String startCodonLower = startCodon.toLowerCase();
            String stopCodonLower = stopCodon.toLowerCase();

            // System.out.println(dnaLower + " " + startCodonLower + " " + stopCodonLower);

            int startIndex = dnaLower.indexOf(startCodonLower);
            int stopIndex = dnaLower.indexOf(stopCodonLower, startIndex+3);
            if (startIndex == -1 || stopIndex == -1) {
                return "";
            }

            gene = dna.substring(startIndex, stopIndex+3);
            if (gene.length() % 3 != 0) {
                return "";
            }

            return gene;
        }

        public void testFindSimpleGene(){
            String dna = "ATG_VALIDGENE1_TAA";
            String gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_VALIDGENE1_TAA_INVALIDBIT";
            gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "atg_validgene1_taa_invalidbit";
            gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "MISSINGSTART_VALIDGENE1_TAA";
            gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_VALIDGENE1_MISSINGEND"; 
            gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);

            dna = "ATG_INVALIDGENE_TAA";
            gene = findSimpleGene(dna, "ATG", "TAA");
            System.out.println("DNA is "+dna);
            System.out.println("Gene is "+gene);
        }
    }

    public class Part3 {
        public boolean twoOccurrences(String stringa, String stringb) {
            int firstIndex = stringb.indexOf(stringa);
            int secondIndex = stringb.indexOf(stringa, firstIndex+stringa.length());

            if (firstIndex != -1 && secondIndex != -1) {
                System.out.println(stringa + "||" + stringb + "||" + true);
                return true;
            } else {
                System.out.println(stringa + "||" + stringb + "||" + false);
                return false;
            }
            
        }

        public String lastPart(String stringa, String stringb) {
            int firstIndex = stringb.indexOf(stringa);
            if (firstIndex != -1) {
                String lastPart = stringb.substring(firstIndex+stringa.length(), stringb.length());
                return lastPart;
            }
            
            return stringb;
        }
    }

    public class Part4 {
        public boolean substringExists(String subString, String fullString){
            if (fullString.indexOf(subString) != -1) {
                return true;
            } else {
                return false;
            }
        }

        public void findWebLinks(String url) {
            URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
            Iterable<String> contents = ur.lines();
            for (var s: contents){
                if (substringExists("youtube.com", s)) {
                    int firstIndex = s.indexOf("\"");
                    int lastIndex = s.lastIndexOf("\"");
                    System.out.println(s.substring(firstIndex+1, lastIndex));
                }
            }
        }
    }

    public static void main(String[] args){

        // StringsFirstAssignments sfa = new StringsFirstAssignments();
        // StringsFirstAssignments.Part1 p1 = sfa.new Part1();
        // System.out.println(p1.testFindSimpleGene());

        // StringsFirstAssignments sfa = new StringsFirstAssignments();
        // StringsFirstAssignments.Part2 p2 = sfa.new Part2();
        // System.out.println(p3.testFindSimpleGene());
        
        // StringsFirstAssignments sfa = new StringsFirstAssignments();
        // StringsFirstAssignments.Part3 p3 = sfa.new Part3();
        // System.out.println(p3.twoOccurrences("by", "A story by Abby Long"));
        // System.out.println(p3.lastPart("zoo", "forest"));

        StringsFirstAssignments sfa = new StringsFirstAssignments();
        StringsFirstAssignments.Part4 p4 = sfa.new Part4();
        p4.findWebLinks("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }

}
