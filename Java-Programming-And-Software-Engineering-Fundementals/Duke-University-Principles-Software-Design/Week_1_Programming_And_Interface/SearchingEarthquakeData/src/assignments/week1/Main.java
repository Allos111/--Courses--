package assignments.week1;

public class Main
{
    public static void main(String[] args)
    {
        EarthQuakeClient earthQuakeClientObject = new EarthQuakeClient();
        ClosestQuakes closestQuakesObject = new ClosestQuakes();
        LargestQuakes largestQuakesQuake = new LargestQuakes();
        //earthQuakeClientObject.createCSV();
        //earthQuakeClientObject.bigQuakes();
        //earthQuakeClientObject.closeToMe();
        //closestQuakesObject.findClosestQuakes();
        //earthQuakeClientObject.quakesOfDepth();
        //earthQuakeClientObject.quakesByPhrase();
        largestQuakesQuake.findLargestQuakes();
    }
}
