package com.willmayala;

/**
 * The class Rating is also a POJO that stores data about one rating of an item.
 * @author Allos111
 */

// An immutable passive data object (PDO) to represent the rating data
public class Rating implements Comparable<Rating>
{
    // Two private variables to represent information about a rating
    private String item; // the IMDB ID of the movie that being rated
    private double value; //The value of the actual rating

    public Rating (String anItem, double aValue)
    {
        item = anItem;
        value = aValue;
    }

    // Returns item being rated
    public String getItem ()
    {
        return item;
    }

    // Returns the value of this rating (as a number so it can be  used in calculations)
    public double getValue ()
    {
        return value;
    }

    // Returns a String containing all the rating information
    public String toString ()
    {
        return "[" + "IMDB's ID: " + getItem() + ", " + "Rating: " + getValue() + "]";
    }

    // Compare this rating (The actual rating) with another rating
    public int comparableTo (Rating other)
    {
//        if (value < other.value) return -1;
//        if (value > other.value) return 1;
//
//        return 0;
        return Double.compare(value, other.value);

    }

    @Override
    public int compareTo(Rating o)
    {
        return 0;
    }
}
