package assignments.week1;

import java.util.*;

public class EarthQuakeClient
{
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin)
    {
        //an arraylist of all quakes bigger than magMin
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        //TODO
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getMagnitude() > magMin)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getLocation().distanceTo(from) < distMax)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list)
    {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    public void bigQuakes()
    {
        // Reading from the parser and then putting all the quakes in the ArrayList of QuakeEntry list.
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        System.out.println("\n");
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");
        /*
        for (QuakeEntry qe : list)
        {
            if (qe.getMagnitude() > 5.0)
            {
                System.out.println(qe);
            }
        }
        */
        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig)
        {
            System.out.println(qe);
        }
    }

    /**
     * This method simply process the data by creating a CSV format of the file
     */
    public void createCSV()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void closeToMe()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++)
        {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
        System.out.println("Found " + close.size() + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByDepth (ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData)
        {
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth)
            {
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        ArrayList<QuakeEntry> quakesByDepth = filterByDepth(list, -4000.0, -2000.0);
        for (QuakeEntry qe : quakesByDepth)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + quakesByDepth.size() + " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase (ArrayList<QuakeEntry> quakeData, String where, String phrase)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        if (where.equals("start"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if(qe.getInfo().startsWith(phrase))
                {
                    answer.add(qe);
                }
            }
        }

        else if (where.equals("any"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if (qe.getInfo().contains(phrase))
                {
                    answer.add(qe);
                }
            }
        }
        else if (where.equals("end"))
        {
            for (QuakeEntry qe : quakeData)
            {
                if (qe.getInfo().endsWith(phrase))
                {
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void quakesByPhrase()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());

        ArrayList<QuakeEntry> quakesByPhrase = filterByPhrase(list, "any", "Can");
        for (QuakeEntry qe : quakesByPhrase)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + quakesByPhrase.size() + " quakes that match that criteria");
    }
}
