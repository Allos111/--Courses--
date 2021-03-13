package assignments.week1;

public class MagnitudeFilter implements Filter
{
    private double magMin;
    private double magMax;
    private String name = "MagnitudeFilter";

    public MagnitudeFilter (double min, double max)
    {
        magMin = min;
        magMax = max;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getMagnitude() >= magMin && (qe.getMagnitude() <= magMax));
    }

    public String getName ()
    {
        return name;
    }
}
