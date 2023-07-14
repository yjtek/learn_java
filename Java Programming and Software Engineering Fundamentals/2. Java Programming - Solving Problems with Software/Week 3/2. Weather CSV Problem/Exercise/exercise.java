import edu.duke.*;
import org.apache.commons.csv.*;

public class exercise {

    public CSVRecord getSmallerRecord(CSVRecord existing_record, CSVRecord new_record, String feature){
        if (
            existing_record == null ||
            existing_record.get(feature).equals("N/A") || 
            existing_record.get(feature).equals("-9999")
        ) {
            return new_record;
        }
        else if (
            new_record == null ||    
            new_record.get(feature).equals("N/A") || 
            new_record.get(feature).equals("-9999")    
        ){
            return existing_record;
        }

        if (Double.parseDouble(existing_record.get(feature)) <= Double.parseDouble(new_record.get(feature))){
            return existing_record;
        }
        else {
            return new_record;
        }
    }

    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestRecord = null;
        for (CSVRecord r: parser){
            smallestRecord = getSmallerRecord(smallestRecord, r, "TemperatureF");
        }
        return smallestRecord;
    }

    public void testColdestHourInFile(){
        // FileResource fr = new FileResource("./nc_weather/2012/weather-2012-01-01.csv");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(coldestHourInFile(parser));
    }

    public String fileWithColdestTemperature(){
        CSVRecord smallestRecord = null;
        String fileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (java.io.File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord smallestRecordInFile = coldestHourInFile(parser);
            System.out.println(smallestRecordInFile.get("TemperatureF"));
            if (
                smallestRecord == null || 
                Double.parseDouble(smallestRecordInFile.get("TemperatureF")) < Double.parseDouble(smallestRecord.get("TemperatureF"))
            ){
                smallestRecord = smallestRecordInFile;
                fileName = f.getName();
            }
            System.out.println(fileName);
        }
        System.out.println(fileName);

        FileResource fr = new FileResource("nc_weather/2013/"+fileName);
        CSVParser parserColdest = fr.getCSVParser();
        String coldestHour = coldestHourInFile(parserColdest).get("TemperatureF");

        System.out.println("Coldest day was in file "+fileName);
        System.out.println("Coldest temperature on that day was "+coldestHour);
        System.out.println("All the Temperatures on the coldest day were:");

        CSVParser parserColdest2 = fr.getCSVParser();
        for (CSVRecord r: parserColdest2){
            System.out.println(fileName + " " + r.get("DateUTC") + ": " + r.get("TemperatureF"));
        }

        return fileName;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord r: parser){
            lowestHumidity = getSmallerRecord(lowestHumidity, r, "Humidity");
        }
        return lowestHumidity;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord r = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + r.get("Humidity") + " at " + r.get("DateUTC"));
    }

    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        for (java.io.File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            lowestHumidity = getSmallerRecord(lowestHumidity, lowestHumidityInFile(parser), "Humidity");
        }
        return lowestHumidity;
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord r = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + r.get("TemperatureF") + " at " + r.get("DateUTC"));
    }

    public Double averageTemperatureInFile(CSVParser parser){
        Double countRecords = 0.0;
        Double sumTemperature = 0.0;
        for (CSVRecord r: parser){
            countRecords += 1;
            sumTemperature += Double.parseDouble(r.get("TemperatureF"));
            System.out.println(r.get("TemperatureF"));    
        }
        System.out.println(sumTemperature);
        System.out.println(countRecords);
        return sumTemperature/countRecords;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        Double temp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + temp);
    }

    public Double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        Double countRecords = 0.0;
        Double sumTemperature = 0.0;
        for (CSVRecord r: parser){
            if(Double.parseDouble(r.get("Humidity")) >= value){
                countRecords += 1;
                sumTemperature += Double.parseDouble(r.get("TemperatureF"));
            }
        }
        if (countRecords == 0.0){
            System.out.println("No temperatures with that humidity");
            return null;
        }
        System.out.println("Average Temp when high Humidity is "+ sumTemperature/countRecords);
        return sumTemperature/countRecords;
    }

    public static void main(String[] args){
        exercise e = new exercise();

        // FileResource fr = new FileResource("./nc_weather/2014/weather-2014-06-29.csv");
        // CSVParser parser = fr.getCSVParser();
        // System.out.println(e.coldestHourInFile(parser).get("TemperatureF"));

        e.fileWithColdestTemperature();

        // FileResource fr = new FileResource("./nc_weather/2014/weather-2014-07-22.csv");
        // CSVParser parser = fr.getCSVParser();
        // System.out.println(e.lowestHumidityInFile(parser).get("DateUTC"));
        
        // System.out.println(e.lowestHumidityInManyFiles().get("DateUTC"));

        // FileResource fr = new FileResource("./nc_weather/2013/weather-2013-08-10.csv");
        // CSVParser parser = fr.getCSVParser();
        // System.out.println(e.averageTemperatureInFile(parser));

        // FileResource fr = new FileResource("./nc_weather/2013/weather-2013-09-02.csv");
        // CSVParser parser = fr.getCSVParser();
        // System.out.println(e.averageTemperatureWithHighHumidityInFile(parser, 80));

    }
    
}
