package assignments.week1;

import java.util.*;

public class EarthQuakeClient2
{
    public EarthQuakeClient2()
    {
        // TODO Auto-generated constructor stub
    }

    /**
     * This method returns an ArrayList of QuakeEntry's from quakeData that meet the requirements of Filter f's satisfies method.
     * @param quakeData is QuakeEntry.
     * @param f a Filter.
     * @return an ArrayList of QuakeEntry's from quakeData.
     */
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f)
    {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData)
        {
            if (f.satisfies(qe)) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /**
     * This method creates an earthQuakeParser to read in an earthquake data file, creating an ArrayList
     * of QuakeEntries. It then creates a MinMagFilter with minimum magnitude of 4.0, and then calls the filter method
     * with the MinMagFilter to create an ArrayList of QuakeEntry's that have magnitude 4.0 or greater.
     */
    public void quakesWithFilter()
    {
        System.out.println("\n");
        System.out.println("quakesWithFilter\n");
        System.out.println("------------------");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        System.out.println("\n");
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes\n");

        //Filter filterDistance = new DistanceFilter(55.7308, 9.1153, 3_000_000.0);
        //Filter filterPhrase = new PhraseFilter("end", "a");
        Filter filterMag  = new MagnitudeFilter(0.0, 5.0);
        //Filter filterDepth = new DepthFilter(-180000.0, -30000.0);

        ArrayList<QuakeEntry> m7 = filter(list, filterMag);
        //m7 =  filter (m7, filterDistance);

        for (QuakeEntry qe : m7)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + m7.size() + " quakes that match that criteria");
    }

    public void createCSV()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
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

    public void testMatchAllFilter ()
    {
        System.out.println("\n");
        System.out.println("testMatchAllFilter");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        System.out.println("\n");
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes\n");

        MatchAllFilter matchAllFilter = new MatchAllFilter();
        //matchAllFilter.addFilter(new MagnitudeFilter(0.0, 2.0));
        //matchAllFilter.addFilter(new DepthFilter(-100_000.0, -10_000.0));
        matchAllFilter.addFilter(new PhraseFilter("end", "a"));
        //matchAllFilter.addFilter(new DistanceFilter(39.7392,-104.9903, 1000_000_000.0));
        //matchAllFilter.addFilter(new PhraseFilter("any", "Ca"));

        ArrayList<QuakeEntry> m7 = filter(list, matchAllFilter);
        for (QuakeEntry qe : m7)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + m7.size() + " quakes that match that criteria");
        System.out.println("Filters used are " + matchAllFilter.getName() + "\n");
    }

    public void testMatchAllFilters2 ()
    {
        System.out.println("\n");
        System.out.println("testMatchAllFilter2\n");
        System.out.println("---------------------");
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        System.out.println("\n");
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes\n");

        //Location loc = new Location(36.1314, -95.9372);// to use with the distance filter

        MatchAllFilter matchAllFilter = new MatchAllFilter();
        //matchAllFilter.addFilter(new MagnitudeFilter(0.0, 5.0));
        //matchAllFilter.addFilter(new DepthFilter(55_7308.0, 9_1153.0));
        //matchAllFilter.addFilter(new PhraseFilter("any", "a"));
        matchAllFilter.addFilter(new DistanceFilter(55.7308, 9.1153,3000000));
        //matchAllFilter.addFilter(new PhraseFilter("any", "Ca"));

        ArrayList<QuakeEntry> m7 = filter(list, matchAllFilter);
        for (QuakeEntry qe : m7)
        {
            System.out.println(qe);
        }
        System.out.println("Found " + m7.size() + " quakes that match that criteria");
        System.out.println("Filters used are " + matchAllFilter.getName() + "\n");
    }
}
