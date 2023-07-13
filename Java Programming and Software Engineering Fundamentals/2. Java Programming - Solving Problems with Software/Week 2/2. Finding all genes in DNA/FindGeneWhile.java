public class FindGeneWhile {
    public String findGeneWhile(String dna){
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex+3);
        while (currIndex != -1){
            if((currIndex - startIndex)%3 == 0){
                return dna.substring(startIndex, currIndex+3);
            }
            else {
                currIndex = dna.indexOf("TAA", currIndex+1);
            }
        }
        return "";
    }

    public void testFindGeneWhile(){
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA is "+dna);
        String gene = findGeneWhile(dna);
        System.out.println("Gene is "+gene);

        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA is "+dna);
        gene = findGeneWhile(dna);
        System.out.println("Gene is "+gene);

        dna = "CGATGGTTGATAAGCCTAAGCTAAA";
        System.out.println("DNA is "+dna);
        gene = findGeneWhile(dna);
        System.out.println("Gene is "+gene);
    }

    public static void main(String[] args){
        FindGeneWhile fgw = new FindGeneWhile();
        fgw.testFindGeneWhile();
    }
}
