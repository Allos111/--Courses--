package com.willmayala;

/**
 * This is an implementation of the selection sort algorithm
 *
 * @author (Toussaint Will Mayala)
 * @version (April 2020)
 */

import java.util.ArrayList;

public class QuakeSortInPlace
{
    public QuakeSortInPlace()
    {
        // TODO Auto-generated constructor stub
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from)
    {
        int minIdx = from; //The index position we want to start at(find the smallest mag from this point over the ArrayList
        for (int i = from + 1; i < quakes.size(); i++)
        {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude())
            {
                minIdx = i;
            }
        }
        return minIdx;
    }

    public int getLargestDepth (ArrayList<QuakeEntry> quakeData, int from)
    {
        int largestIndex = from;
        for (int i = from + 1; i < quakeData.size(); i++)
        //for (int i = from + 1; i < 50; i++)
        {
            if (quakeData.get(i).getDepth() > quakeData.get(largestIndex).getDepth())
            {
                largestIndex = i;
            }
        }
        return largestIndex;
    }

    public void sortByLargestDepth (ArrayList<QuakeEntry> in)
    {
        //for (int i = 0; i < in.size(); i++)
        for (int i = 0; i < 50; i++)
        {
                int largestIndex = getLargestDepth(in, i);
                QuakeEntry qe = in.get(i);
                QuakeEntry qLarg = in.get(largestIndex);
                in.set(i, qLarg);
                in.set(largestIndex, qe);
        }
    }

    public void sortByMagnitude(ArrayList<QuakeEntry> in)
    {
        System.out.println("This is a test");
        for (int i = 0; i < in.size(); i++)
        {
            int minIdx = getSmallestMagnitude(in, i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }

    }

    /**
     * This method makes one pass of the bubbleSort on the ArrayList.
     * It should take advantage of the fact that the last numSorted elements are already in sorted order.
     * @param quakeData ArrayList of QuakeEntry to be sorted.
     * @param numSorted number of time this method has already been called on this ArrayList.
     */
    public void onePassBubbleSort (ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        int quakeDataSize = quakeData.size();
        for (int k = 0; k < quakeDataSize - 1 - numSorted; k++)
        {
            QuakeEntry q1 = quakeData.get(k);
            QuakeEntry q2 = quakeData.get(k + 1);

            if (q1.getMagnitude() > q2.getMagnitude())
            {
                quakeData.set(k, q2);
                quakeData.set(k + 1, q1);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort (ArrayList<QuakeEntry> in)
    {
        for (int k = 0; k < in.size(); k++)
        {
            onePassBubbleSort(in, k);
            System.out.println("Printing Quakes after pass  " + k);
            for (QuakeEntry qe : in)
            {
                System.out.println(qe);
            }
        }
    }

    public void onePassBubbleSortByDepth (ArrayList<QuakeEntry> quakeData, int numSorted)
    {
        int quakeDataSize = quakeData.size();
        for (int k = 0; k < quakeDataSize - 1 - numSorted; k++)
        {
            QuakeEntry q1 = quakeData.get(k);
            QuakeEntry q2 = quakeData.get(k + 1);

            if (q1.getDepth() > q2.getDepth())
            {
                quakeData.set(k, q2);
                quakeData.set(k + 1, q1);
            }
        }
    }

    public void sortByDepthWithBubbleSort (ArrayList<QuakeEntry> in)
    {
        for (int k = 0; k < in.size(); k++)
        {
            onePassBubbleSortByDepth(in, k);
            System.out.println("Printing Quakes after pass  " + k);
            for (QuakeEntry qe : in)
            {
                System.out.println(qe);
            }
        }
    }

    /**
     * This method returns true if the earthquakes are in sorted oder by magnitude from smallest too largest.
     * @param quakes ArrayList of QuakeEntry to be sorted.
     * @return  a boolean.
     */
    public boolean checkInSortedOrder (ArrayList<QuakeEntry> quakes)
    {
        int quakeSize = quakes.size();
        for (int k = 0; k < quakeSize - 1; k++)
        {
            QuakeEntry q1 = quakes.get(k);
            QuakeEntry q2 = quakes.get(k + 1);

            if((q1.getMagnitude() > q2.getMagnitude()))
            {
                return false;
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in)
    {
        int inSize = in.size();
        //int countPass = 0;
        for (int k = 0; k < inSize; k++)
        {
            if (checkInSortedOrder(in))
            {
                System.out.println("Total number of passes: " + k);
                break;
            }
            onePassBubbleSort(in, k);
//            System.out.println("Printing quakes after pass: " + k);
//            for (QuakeEntry qe : in)
//                System.out.println(qe);
        }
        //System.out.println("Total number of passes: " + countPass);
    }

    public void sortByMagnitudeWithCheck (ArrayList<QuakeEntry> in)
    {
        int inSize = in.size();
        for (int k = 0; k < inSize; k++)
        {
            if (checkInSortedOrder(in))
            {
                System.out.println("Total number of passes: " + k);
                break;
            }

            //for (int i = 0; i < in.size(); i++)
            //{
                int minIdx = getSmallestMagnitude(in, k);
                QuakeEntry qi = in.get(k);
                QuakeEntry qmin = in.get(minIdx);
                in.set(k, qmin);
                in.set(minIdx, qi);
            //}
        }
    }


    public void createCSV()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                    qe.getLocation().getLatitude(),
                    qe.getLocation().getLongitude(),
                    qe.getMagnitude(),
                    qe.getInfo());
        }
    }

    public void testSort()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
       // String source = fileResource.asString();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/earthquakeDataSampleSix2.atom";
        //String source = "data/earthQuakeDataDec6sample1.atom"; //Assignment question One.
        //String source = "data/earthQuakeDataDec6sample2.atom"; //Assignment question One.
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        //String source = "data/nov20quakedata.atom";
        //String source = "data1/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        //System.out.println("read data for " + list.size() + " quakes");
        //sortByMagnitude(list);
        sortByLargestDepth(list);
        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByDepthWithBubbleSort(list);
        //sortByMagnitudeWithCheck(list);

        for (QuakeEntry qe : list)
        {
            System.out.println(qe);
        }
        System.out.println("===============================================");
        System.out.println("read data for " + list.size() + " sorted quakes");
        //System.out.println(list.get(50));
    }
}
