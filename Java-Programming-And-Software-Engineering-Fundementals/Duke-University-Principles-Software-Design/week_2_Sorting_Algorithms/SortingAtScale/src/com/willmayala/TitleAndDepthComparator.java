package com.willmayala;


import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>
{
    @Override
    public int compare(QuakeEntry q1, QuakeEntry q2)
    {
        int compare1 = q1.getInfo().compareTo(q2.getInfo());
        int compare2 = q2.getInfo().compareTo(q1.getInfo());

        if (compare1 > compare2) {
            return 1;
        }
        else if (compare1 < compare2)
        {
            return -1;
        }
        else if (compare1 == 0)
        {
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
        return 0;
        // sort by title
        //then sort by depth
//        if (titleCompare == 0)
//            return Double.compare(q1.getDepth(), q2.getDepth());
//        return titleCompare;
    }
}
