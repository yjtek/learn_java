public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna){
        String result = "";
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf("TAA", startIndex+3); //Ignore first 3 indices from wherever start codon is found, because it is ATG. 
        System.out.println("startIndex: "+startIndex);
        System.out.println("stopIndex: "+stopIndex);
        if (startIndex == -1 || stopIndex == -1){
            return result;
        }

        result = dna.substring(startIndex, stopIndex+3);
        return result;
    }

    public void testFindGeneSimple(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "ATGTAA";
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "TTATAA"; //no start codon, what happens??
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);

        dna = "CGATGGTTTG"; //no end codon, what happens??
        System.out.println("DNA strand is "+dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is "+gene);
    }

    public static void main(String[] args){
        FindGeneSimpleAndTest fgsat = new FindGeneSimpleAndTest();
        // fgsat.findGeneSimple("ATG_OMG_TAA");
        fgsat.testFindGeneSimple();
    }
}
