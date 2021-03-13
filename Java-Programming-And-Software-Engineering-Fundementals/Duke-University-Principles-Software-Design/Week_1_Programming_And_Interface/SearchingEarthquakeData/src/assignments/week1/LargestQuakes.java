package assignments.week1;

import java.util.*;

public class LargestQuakes
{
    public void findLargestQuakes()
    {
        EarthQuakeClient earthQuakeClient = new EarthQuakeClient();
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/earthQuakeDataDec6Sample2.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        ArrayList<QuakeEntry> newlist = new ArrayList<QuakeEntry>(list);
        //earthQuakeClient.dumpCSV(list);
        int largestIndex = indexOfLargest(list);
        QuakeEntry quake = list.get(largestIndex);
        double magOfMaxIndex =  quake.getMagnitude();

        ArrayList<QuakeEntry> largest = getLargest(list, 50);
        for (int k = 0; k < largest.size(); k++)
        {
            QuakeEntry entry = largest.get(k);
            System.out.println(entry);
        }
//        for (QuakeEntry qe : list)
//        {
//            System.out.println(qe);
//        }
        System.out.println("The largest earthquake is at index " + largestIndex + " and has the magnitude of " + magOfMaxIndex);
        System.out.println("# quakes found " + list.size());
    }

    public int indexOfLargest (ArrayList<QuakeEntry> data)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(data);
        int largestIndex =  0;
        double maxIndex = 0;
        for (int k = 0; k < copy.size(); k++)
        {
            QuakeEntry quake = data.get(k);
            double mag = quake.getMagnitude();
            if (mag > maxIndex)
            {
                maxIndex = mag;
                largestIndex = k;
            }
        }
        return largestIndex;
    }

    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany)
    {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>(); //ArrayList returned containing the closest quakes
        if (howMany > copy.size())
        {
            howMany = copy.size();
        }
        int largestIndex = 0;
        for (int k = 0; k < howMany; k++)
        {
            largestIndex = indexOfLargest(copy);
            answer.add(copy.get(largestIndex));
            copy.remove(largestIndex);
        }
        return answer;
    }
}
