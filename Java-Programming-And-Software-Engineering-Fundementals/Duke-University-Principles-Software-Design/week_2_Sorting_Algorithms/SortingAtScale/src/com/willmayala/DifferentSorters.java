package com.willmayala;

import javax.crypto.spec.PSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Write a description of class DifferentSorters here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class DifferentSorters
{
    public ArrayList<QuakeEntry> getQuakeData()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/quakedata/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        return parser.read(source);
    }

    public ArrayList<QuakeEntry> getQuakeData(String fname)
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/quakedata/" + fname;
        return parser.read(source);
    }

    public void sortWithCompareTo()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/quakedata/earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list);
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));

    }

    public void sortByMagnitude()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/quakedata/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new MagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

    }

    public void sortByDistance()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        // Location is Durham, NC
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }


    }

    public void testRegEx() {
        // compare last words
        Pattern p = Pattern.compile("\\w+$");

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/quakedata/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        for (QuakeEntry qe : list) {

            Matcher match1 = p.matcher(qe.getInfo());
            if (match1.find()) {
                String last1 = match1.group(0);
                System.out.printf("%s :: %s\n", last1, qe.getInfo());
            }
        }
    }

    // Assignment 2: Title Comparator
    public void sortByTitleAndDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        // ArrayList<QuakeEntry> list = getQuakeData();
        Collections.sort(list, new TitleAndDepthComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        int position = 500;
        System.out.println("Value on position: " + position);
        System.out.println(list.get(position));
    }

    //Assignment 3: Last Word in Title Comparator
    public void sortByLastWordInTitleThenByMagnitude() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "data/earthQuakeDataDec6sample2.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        Collections.sort(list, new TitleLastAndMagnitudeComparator());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }

        int pos = 500;
        System.out.println("Value on position: " + pos);
        System.out.println(list.get(pos));

    }

    public static void main(String[] args) {
        DifferentSorters ds = new DifferentSorters();
//		ds.sortWithCompareTo();
//		ds.testRegEx();
//		ds.sortByTitleAndDepth();
        ds.sortByLastWordInTitleThenByMagnitude();
    }
}
