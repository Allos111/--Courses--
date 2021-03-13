package com.willmayala;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
//        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "data/quakedata/earthQuakeDataWeekDec6sample1.atom";
//        ArrayList<QuakeEntry> list = parser.read(source);
        //QuakeEntry quakeEntry = new QuakeEntry(0.0,0.0,0.0,"String",0.7);
       // String qe = quakeEntry.toString();
        //System.out.println(qe);
        DifferentSorters differentSortersObj  = new DifferentSorters();
        //differentSortersObj.sortWithCompareTo();
        //differentSortersObj.sortByTitleAndDepth();
        differentSortersObj.sortByLastWordInTitleThenByMagnitude();
    }
}
