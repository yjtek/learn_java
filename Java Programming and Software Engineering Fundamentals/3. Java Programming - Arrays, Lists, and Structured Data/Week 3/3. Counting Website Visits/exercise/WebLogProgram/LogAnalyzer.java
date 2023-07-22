package WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.text.SimpleDateFormat;

// import LogEntry.LogEntry;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<LogEntry>();
    }
        
    public void readFile(String filename) {

        // FileResource fr = new FileResource("short-test_log");
        // FileResource fr = new FileResource("weblog-short_log");
        // FileResource fr = new FileResource("weblog1_log");
        FileResource fr = new FileResource("weblog2_log");
        // FileResource fr = new FileResource("weblog3-short_log");
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

    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> visitCounts = new HashMap<String, Integer>();
        
        for (LogEntry le: records){
            String ipAddr = le.getIpAddress();
            if (visitCounts.containsKey(ipAddr)){
                int existingCount = visitCounts.get(ipAddr);
                visitCounts.put(ipAddr, existingCount+1);
            }
            else {
                visitCounts.put(ipAddr, 1);
            }
        }
        return visitCounts;
    }

    public int mostNumberVisitsByIP(HashMap<String,Integer> visitCounts){
        int maxVisits = 0;
        for(String key: visitCounts.keySet()){
            if (visitCounts.get(key) > maxVisits){
                maxVisits = visitCounts.get(key);
            }
        }
        return maxVisits;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> visitCounts){
        int maxVisits = mostNumberVisitsByIP(visitCounts);
        ArrayList<String> iPsWithMostVisits = new ArrayList<String>();
        for (String key: visitCounts.keySet()){
            if (visitCounts.get(key) == maxVisits){
                iPsWithMostVisits.add(key);
            }
        }
        return iPsWithMostVisits;
    }

    public HashMap<String, ArrayList<String>> iPsForDays(){
    // public void iPsForDays(){
        HashMap<String, ArrayList<String>> mapDateToIPs = new HashMap<String, ArrayList<String>>();
        
        for (LogEntry le: records){
            String[] leDate = le.getAccessTime().toString().split("\\s");
            String MMMDD = leDate[1] + " " + leDate[2];
            String ipAddr = le.getIpAddress();
            
            if (mapDateToIPs.containsKey(MMMDD)){
                mapDateToIPs.get(MMMDD).add(ipAddr);
            }
            else {
                ArrayList<String> newArray = new ArrayList<String>();
                newArray.add(ipAddr);
                mapDateToIPs.put(MMMDD, newArray);
            }
        }

        // for (String key: mapDateToIPs.keySet()){
        //     System.out.println(key + mapDateToIPs.get(key));
        // }

        return mapDateToIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapDateToIPs){
        int maxIPCount = 0;
        String maxIPCountDay = "";
        for (String day: mapDateToIPs.keySet()){
            int countIPDays = mapDateToIPs.get(day).size();
            if (countIPDays > maxIPCount){
                maxIPCountDay = day;
            }
        }
        return maxIPCountDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(
        HashMap<String, ArrayList<String>> mapDateToIPs, 
        String date_MMMDD
    ) {
        ArrayList<String> ipOnDate = mapDateToIPs.get(date_MMMDD);
        HashMap<String, Integer> countVisitsPerIP = new HashMap<String, Integer>();
        for (String ip: ipOnDate){
            if (countVisitsPerIP.containsKey(ip)){
                countVisitsPerIP.put(ip, countVisitsPerIP.get(ip) + 1);
            }
            else {
                countVisitsPerIP.put(ip, 1);
            }
        }
        ArrayList<String> ipList = iPsMostVisits(countVisitsPerIP);
        return ipList;
    }
     
}
