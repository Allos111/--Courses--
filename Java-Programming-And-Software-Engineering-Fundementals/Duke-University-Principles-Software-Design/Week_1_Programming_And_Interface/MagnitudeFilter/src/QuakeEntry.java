/**
 *
 */
public class QuakeEntry implements Comparable<QuakeEntry>
{
    //instance fields
    private Location myLocation;
    private String title;
    private double depth;
    private double magnitude;

    public QuakeEntry(double lat, double lon, double mag,
                      String t, double d)
    {
        myLocation = new Location(lat,lon);

        magnitude = mag;
        title = t;
        depth = d;
    }

    public Location getLocation()
    {
        return myLocation;
    }

    public double getMagnitude()
    {
        return magnitude;
    }

    public String getInfo()
    {
        return title;
    }

    public double getDepth()
    {
        return depth;
    }

    /**
     * From the Java Comparable interface, this method provides a single sorting sequence.
     * It is used to compare the current object with the specified object.
     * @param loc is a QuakeEntry object to be compared.
     * @return positive integer if the current object is greater than the specified object.
     * negative integer if the current object is less than the specified object,
     * zero, if the current object is equal to the specified object.
     */
    @Override
    public int compareTo(QuakeEntry loc)
    {
        double difflat = myLocation.getLatitude() - loc.myLocation.getLatitude();
        if (Math.abs(difflat) < 0.001) {
            double diff = myLocation.getLongitude() - loc.myLocation.getLongitude();
            if (diff < 0) return -1;
            if (diff > 0) return 1;
            return 0;
        }
        if (difflat < 0) return -1;
        if (difflat > 0) return 1;

        // never reached
        return 0;
    }

    public String toString()
    {
        return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", myLocation.getLatitude(),myLocation.getLongitude(),magnitude,depth,title);
    }

}
