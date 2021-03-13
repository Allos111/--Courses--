
/**
 * Write a description of class LogAnalyzer here.
 * @author (Will Toussaint Mayala)
 * @version (January 2020)
 */

import edu.duke.FileResource;
import java.io.File;
import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;

    public LogAnalyzer()
    {
        // complete constructor
        records = new ArrayList<LogEntry>();
    }

    public void readFile(String fileName)
    {
        // complete method
        FileResource myFile = new FileResource(fileName);
        for (String line : myFile.lines())
        {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public int countUniqueIPs()
    {
        // uniqueIPs starts as an empty list
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        // for each element logEntry in records
        for (LogEntry logEntry : records)
        {
            // ipAddress is logEntry's ipAddress
            String ipAddress = logEntry.getIpAddress();
            // if ipAddress is not in uniqueIPs
            if (!uniqueIPs.contains(ipAddress))
            {
                // add ipAddress to uniqueIps
                uniqueIPs.add(ipAddress);
            }
        }
        // return the size of uniqueIPs
        return uniqueIPs.size();
    }

    public void printAllHigherThanNum(int num)
    {
        for (LogEntry logEntry : records)
        {
            // status code in LogEntries
            int statusCode = logEntry.getStatusCode();
            // check if statusCode is greater than num
            if (statusCode > num)
            {
                // print statusCode
                System.out.println("Status Code greater than " + num +
                        statusCode);
            }
        }
    }

    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
        ArrayList<String> iPsOneDay = new ArrayList<String>();
        for (LogEntry logEntry : records)
        {
            String logEntryTime = logEntry.getAccessTime().toString();
            String logEntryIp = logEntry.getIpAddress();

            if (logEntryTime.contains(someday))
            {
                if (!iPsOneDay.contains(logEntryIp))
                {
                    iPsOneDay.add(logEntryIp);
                }
            }
        }
        return iPsOneDay;
    }

    public int countUniqueIPsInRange(int low, int high)
    {
        ArrayList<String> unique = new ArrayList<String>();
        for (LogEntry logEntry : records) {
            String ip = logEntry.getIpAddress();
            if (!unique.contains(ip)) {
                int stat = logEntry.getStatusCode();
                if (stat >= low && stat <= high) {
                    unique.add(ip);
                }
            }
        }
        return unique.size();
    }

    public HashMap<String, Integer> countVisitsPerIP()
    {
        // Make an empty HashMap<String, Integer> counts
        HashMap<String, Integer> counts = new HashMap<String, Integer>();
        // for each logEntry in records
        for (LogEntry logEntry : records)
        {
            // ips is logEntry's ipAddress
            String ip = logEntry.getIpAddress();
            // check if ip is in counts
            if (!counts.containsKey(ip))
            {
                // if not: put ip in with a value of 1
                counts.put(ip, 1);
            }
            // if so: update the value to be 1 more
            else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        // counts is the answer
        return counts;
    }

    // From here begins the assignment
    private HashMap<String, Integer> countVisitsPerIP(String day)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (LogEntry logEntry : records)
        {
            if (!getDay(logEntry).equals(day))
            {
                continue;
            }
            String ip = logEntry.getIpAddress();
            if (!map.keySet().contains(ip))
            {
                map.put(ip, 1);
            }
            else {
                map.put(ip, map.get(ip) + 1);
            }
        }
        return map;
    }

    public int mostNumberVisitsByIP(HashMap<String, Integer> ipCounts)
    {
        int maxVisits = 0;
        for (int visits : ipCounts.values())
        {
            if (visits > maxVisits)
            {
                maxVisits = visits;
            }
        }
        return maxVisits;
    }

    private String getDay(LogEntry logEntry)
    {
        String date = logEntry.getAccessTime().toString();
        // assuming that day at the same place
        return date.substring(4, 10);
    }

    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ipCounts)
    {
        int index = 0;
        //index gets the value from the last method in the tester:
        ArrayList<String> maxIPs = new ArrayList<String>();

        for (String s : ipCounts.keySet())
        {
            if (ipCounts.get(s) == index)
            {
                maxIPs.add(s);
            }
        }
        return maxIPs;
    }

    public HashMap<String, ArrayList<String>> iPsForDays()
    {
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        for (LogEntry logEntry : records) {
            String day = getDay(logEntry);
            String ip = logEntry.getIpAddress();

            if (!map.containsKey(day))
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(ip);
                map.put(day, list);
            } else {
                // ensure unique ips are on the list
                if (!map.get(day).contains(ip))
                {
                    map.get(day).add(ip);
                }
            }
        }
        return map;
    }

        public String dayWithMostIPVisits (HashMap < String, ArrayList < String >> map)
        {
            int maxSize = 0;
            String most = null;

            for (String day : map.keySet())
            {
                int size = map.get(day).size();
                if (size > maxSize)
                {
                    maxSize = size;
                    most = day;
                }
            }
            return most;
        }

        public ArrayList<String> iPsWithMostVisitsOnDay (HashMap < String, ArrayList < String >> mapDays,
                String day)
        {
            // output
            ArrayList<String> list = new ArrayList<String>();
            HashMap<String, Integer> visits = countVisitsPerIP(day);

            // find max visits count
            int maxCount = 0;
            for (int count : visits.values())
            {
                if (count > maxCount) maxCount = count;
            }
            // fill output list
            for (String ip : visits.keySet())
            {
                if (visits.get(ip) == maxCount) list.add(ip);
            }
            return list;
        }

        public void printAll ()
        {
            for (LogEntry logEntry : records)
            {
                System.out.println(logEntry);
            }
        }
}