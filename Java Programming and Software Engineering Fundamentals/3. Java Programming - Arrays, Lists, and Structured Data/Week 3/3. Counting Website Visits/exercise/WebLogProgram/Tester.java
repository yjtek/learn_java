package WebLogProgram;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester {
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPs());
    }

    public void testPrintAllGreaterThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        // la.readFile("weblog-short_log");
        la.readFile("weblog2_log");
        System.out.println(la.uniqueIPVisitsOnDay("Sep 24"));
    }

    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        la.readFile("weblog2_log");
        System.out.println(la.countUniqueIPsInRange(400,499));
    }

    public void testCountVisitsPerIP(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        // la.readFile("weblog3-short_log");
        la.readFile("weblog2_log");

        System.out.println(la.countVisitsPerIP());
    }

    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("short-test_log");
        // la.readFile("weblog3-short_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> visitCounts = la.countVisitsPerIP();
        System.out.println(la.mostNumberVisitsByIP(visitCounts));
    }
    
    public void testIPsMostVisits(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        la.readFile("weblog2_log");
        HashMap<String, Integer> visitCounts = la.countVisitsPerIP();
        ArrayList<String> ipWithMostVisits = la.iPsMostVisits(visitCounts);
        for (String ip: ipWithMostVisits){
            System.out.println(ip);
        }
    }

    public void testIPsForDays(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        la.readFile("weblog1_log");
        la.iPsForDays();
    }

    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        // la.readFile("weblog1_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> mapDateToIPs = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(mapDateToIPs));
    }

    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        // la.readFile("weblog3-short_log");
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> mapDateToIPs = la.iPsForDays();
        System.out.println(la.iPsWithMostVisitsOnDay(mapDateToIPs, "Sep 29"));
    }

    public static void main(String[] args){
        Tester t = new Tester();
        t.testIPsWithMostVisitsOnDay();
    }
}
