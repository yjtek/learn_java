package WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {

        // FileResource fr = new FileResource("short-test_log");
        // FileResource fr = new FileResource("weblog-short_log");
        FileResource fr = new FileResource("weblog1_log");
        for (String line: fr.lines()){
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs() {
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    public void printAllHigherThanNum(int num){
        for (LogEntry le: records){
            int statusCode = le.getStatusCode();
            if (statusCode > num){
                System.out.println(le);
            }
        }
    }

    public int uniqueIPVisitsOnDay(String someday) {        
        String[] somedaySplit = someday.split("\\s");
        String somedayMonth = somedaySplit[0];
        String somedayDay = somedaySplit[1];

        ArrayList<String> uniqueIPs = new ArrayList<String>();

        for (LogEntry le: records){
            String dateString = le.getAccessTime().toString();
            String[] strings = dateString.split("\\s");
            String leMonth = strings[1];
            String leDay = strings[2];

            if (somedayMonth.equals(leMonth) && somedayDay.equals(leDay) && !uniqueIPs.contains(le.getIpAddress())) {
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }

    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>(); 
        for (LogEntry le: records){
            int leStatus = le.getStatusCode();
            if (leStatus >= low && leStatus <= high && !uniqueIPs.contains(le.getIpAddress())){
                uniqueIPs.add(le.getIpAddress());
            }
        }
        return uniqueIPs.size();
    }
     
}
