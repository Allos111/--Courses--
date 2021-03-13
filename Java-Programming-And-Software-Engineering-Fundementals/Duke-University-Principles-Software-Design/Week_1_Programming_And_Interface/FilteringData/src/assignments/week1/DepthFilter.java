package assignments.week1;

public class DepthFilter implements Filter
{
    private double depthMin;
    private double depthMax;
    private String name = "DepthFilter";

    public DepthFilter (double min, double max)
    {
        depthMin = min;
        depthMax = max;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        return (qe.getDepth() >= depthMin) && (qe.getDepth() <= depthMax);
    }

    public String getName()
    {
        return name;
    }
}
