package assignments.week1;
/**
 * This class stores and applies many filters
 */

import java.util.ArrayList;

public class MatchAllFilter implements Filter
{
    private ArrayList<Filter> filterArrayList;

    public MatchAllFilter ()
    {
        filterArrayList = new ArrayList<Filter>();
    }

    public void addFilter (Filter f)
    {
        filterArrayList.add(f);
    }

    public boolean satisfies (QuakeEntry qe)
    {
        for (Filter f : filterArrayList)
        {
            if (!f.satisfies(qe))
            {
                return false;
            }
        }
        return true;
    }

    public String getName()
    {
        StringBuilder stringBuilder = new StringBuilder();
        for (Filter f : filterArrayList)
        {
            stringBuilder.append(f.getName()).append(" ");
        }
        return stringBuilder.toString();
    }
}
