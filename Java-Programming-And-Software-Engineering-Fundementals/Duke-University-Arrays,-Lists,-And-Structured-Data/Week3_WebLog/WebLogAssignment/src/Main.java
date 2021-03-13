import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Main
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }

    public void testLogAnalyzer() {
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog3-short_log");
        read.printAll();
    }

    public void testUniqueIP(){
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog2_log");
        System.out.println("There are " + read.countUniqueIPs() + " different IPs");
    }

    public void testStatusCodeHigherThanNum(){
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog1_log");
        read.printAllHigherThanNum(400);
    }

    public void testUniqueIPVisitsOnDay()
    {
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog2_log");
        read.countUniqueIPs();
        ArrayList a = read.uniqueIPVisitsOnDay("Sep 24");
        System.out.println(a.size());
    }

    public void testCountUniqueIPsInRange()
    {
        LogAnalyzer read = new LogAnalyzer();
        read.readFile("weblog2_log");
        System.out.println(read.countUniqueIPsInRange(200,299));
    }

    public static void main(String[] args)
    {
        //Assignment 3
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("weblog2_log");
        HashMap<String,Integer> counts = logAnalyzer.countVisitsPerIP(); // Return a HasMap
        System.out.println(counts);
        System.out.println("\n");

        System.out.println("mostNumberVisitsByIP");
        System.out.println("====================");
        LogAnalyzer logAnalyzer1 = new LogAnalyzer();
        logAnalyzer1.readFile("weblog2_log");
        HashMap mostNumber = logAnalyzer1.countVisitsPerIP();
        System.out.println("The ip with more visits visited the site " + logAnalyzer1.mostNumberVisitsByIP(mostNumber) + " times.");
        System.out.println("\n");

        System.out.println("iPsMostVisits");
        System.out.println("=============");
        System.out.print("These are the ip's with more visits: ");
        System.out.println(logAnalyzer1.iPsMostVisits(mostNumber));
        System.out.println("\n");

        System.out.println("iPsForDays");
        System.out.println("=========");
        HashMap<String,ArrayList<String>> daysIps = logAnalyzer1.iPsForDays();
        System.out.println(daysIps);
        System.out.println("\n");

        System.out.println("\n");
        System.out.println("iPsWithMostVisitsOnDay");
        System.out.println("==========================");
        LogAnalyzer theAnalyzer = new LogAnalyzer();
        theAnalyzer.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> datesIpMap = theAnalyzer.dayCountHash();
        System.out.println("Day with most different IP visits: " + theAnalyzer.dayWithMostIPVisits(datesIpMap));
        System.out.println("iPsWithMostVisitsOnDay: " + theAnalyzer.iPsWithMostVisitsOnDay(datesIpMap, "Sep 29"));
        System.out.println("\n");

        System.out.println("CountUniqueIPs");
        System.out.println("==============");
        LogAnalyzer logAnalyzer4 = new LogAnalyzer();
        logAnalyzer4.readFile("weblog2_log");
        System.out.println(logAnalyzer4.countUniqueIPs() + " unique IP addresses are in the file.");

        System.out.println("\n");
        System.out.println("uniqueIPVisitsOnDay");
        System.out.println("===================");
        LogAnalyzer logAnalyzer2 = new LogAnalyzer();
        logAnalyzer2.readFile("weblog2_log");
        logAnalyzer2.countUniqueIPs();
        ArrayList arraySize = logAnalyzer2.uniqueIPVisitsOnDay("Sep 29");
        System.out.println(arraySize.size());

        System.out.println("\n");
        System.out.println("countUniqueIPsInRange");
        System.out.println("====================");
        LogAnalyzer logAnalyzer3 = new LogAnalyzer();
        logAnalyzer3.readFile("weblog2_log");
        System.out.println(logAnalyzer3.countUniqueIPsInRange(400,499));


    }
}
