package assignments.week1;

public class DistanceFilter implements Filter
{
    private Location location;
    private double distanceMax;
    private String name = "DistanceFilter";

    public DistanceFilter (double lat, double lon, double distMax)
    {
        location = new Location(lat, lon);
        distanceMax = distMax;
        //String name1 = filterName;
    }

    public boolean satisfies (QuakeEntry qe)
    {
        return qe.getLocation().distanceTo(location) <= distanceMax;
    }

    public String getName()
    {
        return name;
    }
}
