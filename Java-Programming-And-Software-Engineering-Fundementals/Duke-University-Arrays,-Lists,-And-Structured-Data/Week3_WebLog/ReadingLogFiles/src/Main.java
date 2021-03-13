import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\n");
        //LogEntry Tester
        System.out.println("LogEntry Tester");
        System.out.println("================");
        LogEntry logEntry = new LogEntry("1.2.3.4", new Date(),
                "example request", 200, 500);
        System.out.println(logEntry);

        LogEntry logEntry1 = new LogEntry("1.2.100.4", new Date(),
                "example request 1", 300,400);
        System.out.println(logEntry1);

        System.out.println("\n");
        //LogAnalyzer Tester
        System.out.println("LogAnalyzer Tester");
        System.out.println("===================");
        LogAnalyzer logAnalyzer = new LogAnalyzer();
        logAnalyzer.readFile("short_test_log.txt");
        logAnalyzer.printAll();

        System.out.println("\n");
        System.out.println("uniqueIps Tester");
        System.out.println("=================");
        LogAnalyzer logAnalyzer1 = new LogAnalyzer();
        logAnalyzer1.readFile("short_test_log.txt");
        int uniqueIps = logAnalyzer1.countUniqueIPs();
        System.out.println("There are " + uniqueIps + "IPs");

        System.out.println("\n");
        System.out.println("Print All log Higher Than ");
        System.out.println("=========================");
        LogAnalyzer logAnalyzer2 = new LogAnalyzer();
        logAnalyzer2.readFile("weblog1_log");
        logAnalyzer2.printAllHigherThanNum(400);

        System.out.println("\n");
        System.out.println("unique Ip Visits On Day");
        System.out.println("=======================");
        LogAnalyzer logAnalyzer3 = new LogAnalyzer();
        logAnalyzer3.readFile("weblog1_log");
        ArrayList<String> unique = new ArrayList<String>();
        unique =  logAnalyzer3.uniqueIPVisitsOnDay("Mar 24");
        System.out.println(unique.size());

        System.out.println("\n");
        System.out.println("count Unique Ips In Range");
        System.out.println("=========================");
        LogAnalyzer logAnalyzer4 = new LogAnalyzer();
        logAnalyzer4.readFile("weblog1_log");
        int countUnique = logAnalyzer4.countUniqueIPsInRange(200, 299);
        System.out.println("Counts: " + countUnique);

        System.out.println("\n");
        System.out.println("HashMap<String, Integer> countVisitsPerIP()");
        System.out.println("===========================================");
        LogAnalyzer logAnalyzer5 = new LogAnalyzer();
        logAnalyzer5.readFile("short_test_log.txt");
        HashMap<String, Integer> counts = logAnalyzer5.countVisitsPerIP();
        System.out.println(counts);

        System.out.println("\n");
        System.out.println("mostNumberVisitsByIP");
        System.out.println("=====================");
        LogAnalyzer logAnalyzer6 = new LogAnalyzer();
        logAnalyzer6.readFile("weblog3-short_log");
        HashMap<String, Integer> mostNumber = logAnalyzer6.countVisitsPerIP();
        int count = logAnalyzer6.mostNumberVisitsByIP(mostNumber);
        System.out.println("Most Number Visits By Ip: " + count);

        System.out.println("\n");
        System.out.println("IPsMostVisits");
        System.out.println("=============");
        LogAnalyzer logAnalyzer7 = new LogAnalyzer();
        logAnalyzer7.readFile("weblog3-short_log");
        ArrayList<String> iPsMost = logAnalyzer7.iPsMostVisits(mostNumber);
        System.out.println(iPsMost);
    }
}
