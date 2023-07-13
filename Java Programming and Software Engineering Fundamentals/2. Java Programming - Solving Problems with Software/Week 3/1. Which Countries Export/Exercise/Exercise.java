// package Exercise;

import edu.duke.*;
import org.apache.commons.csv.*;

public class Exercise {
    public void tester(){
        FileResource fr = new FileResource("exportdata.csv");
        // FileResource fr = new FileResource("exportsmall.csv");
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));

        fr = new FileResource("exportdata.csv");
        parser = fr.getCSVParser();
        // listExportersTwoProducts(parser, "gold", "diamonds");
        listExportersTwoProducts(parser, "gold", "diamonds");
        
        fr = new FileResource("exportdata.csv");
        parser = fr.getCSVParser();        
        System.out.println(numberOfExporters(parser, "sugar"));

        fr = new FileResource("exportdata.csv");
        parser = fr.getCSVParser();        
        bigExporters(parser, "$999,999,999,999");

    }

    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record: parser){
            if (record.get("Country").equals(country)){
                return country + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord r: parser){
            if ((r.get("Exports").indexOf(exportItem1) != -1) && (r.get("Exports").indexOf(exportItem2) != -1)) {
                System.out.println(r.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int counter = 0;
        for (CSVRecord r: parser){
            if (r.get("Exports").indexOf(exportItem) != -1){
                counter++;
            }
        }
        return counter;
    }

    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord r: parser){
            if (amount.length() < r.get("Value (dollars)").length()){
                System.out.println(r.get("Country") + " : " + r.get("Value (dollars)"));
            }
        }
    }

    public static void main(String[] args){
        Exercise ex = new Exercise();
        ex.tester();
    }
}
