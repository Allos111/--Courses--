/**
 * This program finds out how many times each codon occurs in a strand
 * of DNA based on reading frames.
 * @uthor Will Mayala Allos111
 * @version November 2019
 */

import edu.duke.FileResource;
import java.util.HashMap;

public class CodonCount
{
    private HashMap<String, Integer> mapDna;//Store a HashMap to map DNA codons to their count.

    /**
     * Constructor for the objects of the class
     */
    public CodonCount()
    {
        mapDna = new HashMap<String, Integer>();
    }

    /**
     * The method builds a new map of codons mapped to their counts from
     * the string dna with the reading frame with the positions start 0, 1, 2
     * @param  start is the position that determine the reading frame.
     * @param  dna the Strand of DNA to work on.
     * @return  Nothing.
     */
    private void buildCodonMap(int start, String dna)
    {
        mapDna.clear(); //make sure the map is empty before building it
        String currentCodon; //Store the current codon found
        for(int k = 0; k < (dna.length() - start)/3; k++)//Loop over the String Dna
        {
            currentCodon = dna.substring(start + k * 3, start + k * 3 + 3);
            //System.out.println("Printing the String current/Printed from buidCodonMap: " + currentCodon);
            if (!mapDna.containsKey(currentCodon))
            {
                mapDna.put(currentCodon, 1);//currentCodon is the key, and 1 is the value
            }
            else
            {
                mapDna.put(currentCodon, mapDna.get(currentCodon) + 1);
            }
        }
    }

    /**
     * This method returns a String, the codon in a reading frame that has the largest count.
     */
    private String getMostCommonCodon()
    {
        int value = 0;
        int largestcount = 0;
        String largestkey = null;
        for(String key : mapDna.keySet())
        {
            value = mapDna.get(key);
            if (largestcount < value)
            {
                largestcount = value;
                largestkey = key;
            }
        }
        return largestkey;
    }

    /**
     * This method prints all the codons in the HashMap along with their counts
     * if their count is between start and end, inclusive.
     */
    private void printCodonCounts(int start, int end)
    {
        int value = 0;
        for(String key : mapDna.keySet())
        {
            value = mapDna.get(key);
            if (value >= start && value <= end)
                System.out.println( key + " " + value + "\t");
        }
    }

    public void theTester()
    {
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        int start = 0;
        int end = 100_000;

        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in "+
                mapDna.size() + " unique codons" + "\t");
        String largest = getMostCommonCodon();
        System.out.println(" and most common codon is " + largest + " with count "+
                mapDna.get(largest)+"\t");
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:" + "\t");
        printCodonCounts(start, end);
        System.out.println();
        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in "+
                mapDna.size()+ " unique codons"+"\t");
        largest = getMostCommonCodon();
        System.out.println(" and most common codon is " + largest + " with count "+
                mapDna.get(largest) + "\t");
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:" + "\t");
        printCodonCounts(start, end);
        System.out.println();
        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in "+
                mapDna.size()+" unique codons"+"\t");
        largest = getMostCommonCodon();
        System.out.println("and most common codon is " + largest + " with count "+
                mapDna.get(largest) + "\t");
        System.out.println("Counts of codons between " + start + " and " + end + " inclusive are:" + "\t");
        System.out.println();
        printCodonCounts(start, end);
    }
}
