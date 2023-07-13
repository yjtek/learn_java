import edu.duke.*;

public class StringsThirdAssignments {
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
                startIndex = dna.indexOf(gene, startIndex+1) + gene.length();
                System.out.println("gene: "+gene);
            }
        }

        public StorageResource getAllGenes(String dna){
            int startIndex = 0;
            StorageResource geneList = new StorageResource();    
            while (startIndex <= (dna.length() - 3)){
                String gene = findGene(dna.substring(startIndex, dna.length()));
                if (gene.length() == 0) {
                    break;
                }
                startIndex = dna.indexOf(gene, startIndex+1) + gene.length();
                geneList.add(gene);
            }
            return geneList;
        }

        public float countCGRatio(String dna){
            long countCs = dna.toLowerCase().chars().filter(ch -> ch == 'c').count();
            long countGs = dna.toLowerCase().chars().filter(ch -> ch == 'g').count();
            // System.out.println((float) countCs/countGs);
            return ((float)countCs)/countGs;
        }

        public int countCTG(String dna){
            String[] codons = dna.split("(?<=\\G...)");
            int counter = 0;
            for (String c: codons){

                if (c.equals("CTG")){
                    counter++;
                }
            }
            return counter;
        }

        public void processGenes(StorageResource sr){
            int count_lt_60char = 0;
            int count_gt_cgr = 0;
            int longest_gene_length = 0;
            int total_ctg_count = 0;
            int count_genes = 0;

            int i = 0;
            for (String g: sr.data()){
                System.out.println("s" + i + " = \""+ g + "\"");
                i++;
                count_genes++;

                if (g.length() > 60){
                    //System.out.println(g);
                    count_lt_60char++;
                }

                if (countCGRatio(g) >= 0.35){
                    //System.out.println(g);
                    count_gt_cgr++;
                }

                if (g.length() > longest_gene_length){
                    longest_gene_length = g.length();
                }
                
                total_ctg_count = total_ctg_count + countCTG(g);
                
            }
            System.out.println("Count genes: "+count_genes);
            System.out.println("Count strings longer than 60: "+count_lt_60char);
            System.out.println("Count strings CGR > 0.35: "+count_gt_cgr);
            System.out.println("Longest gene length: "+longest_gene_length);
            System.out.println("Total CTG Count: "+total_ctg_count);
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

        public void testGetAllGenes(){
            String dna = "_WTF_ATG_A_TAA_SOMETHINGAFTER_ATG_B_TAA_SOMETHINGELSE";
            StorageResource geneList = getAllGenes(dna);
            for (String g: geneList.data()){
                System.out.println(g);
            }
        }
        
        public void testCountCTG(){
            String dna = "CCCC_GGGGG";
            System.out.println(countCTG(dna));
        }
    
        public void testProcessGenes(){
            FileResource fr = new FileResource("GRch38dnapart.fa");
            String dna = fr.asString().toUpperCase();
            // printAllGenes(dna);
            StorageResource sr = getAllGenes(dna);
            System.out.println(sr.size());
            processGenes(sr);
        }
    }

    public static void main(String[] args){
        StringsThirdAssignments sta = new StringsThirdAssignments();
        StringsThirdAssignments.Part1 p1 = sta.new Part1();
        p1.testProcessGenes();
    } 
}
