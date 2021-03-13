package assignments.week1;

/**
 * Write a description of class MinMaxFilter here.
 *
 * @author (Toussaint Will Mayala)
 * @version (April 2020)
 */
public class MinMagFilter implements Filter
{
    private double magMin; //minimum magnitude of earthquake to consider for filtering
    private String name = "MinimumMagnitude§Filter";

    public MinMagFilter(double min)
    {
        magMin = min;
    }

    /**
     * This method returns true if if qe has a magnitude greater than or equal to magMin
     * @param qe represent a QuakeEntry object
     * @return a boolean
     */
    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getMagnitude() >= magMin;
    }

    public String getName ()
    {
        return name;
    }
}
