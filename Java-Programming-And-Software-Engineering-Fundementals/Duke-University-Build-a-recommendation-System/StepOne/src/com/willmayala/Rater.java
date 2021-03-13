package com.willmayala;

/**
 * This class keeps track of one rater and all their ratings.
 */

import java.util.ArrayList;

public class Rater
{
    private String myID; //the Rater's ID
    private ArrayList<Rating> myRatings; // the Rater's ratings

    public Rater (String id)
    {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }

    // this creates a new  rating and adds it to myRatings
    public void addRating (String item, double rating)
    {
        myRatings.add(new Rating (item, rating));
    }

    // This prevents from duplicating one rater's rating
    public boolean hasRating (String item)
    {
        for (Rating myRating : myRatings)
        {
            if (myRating.getItem().equals(item))
            {
                return true;
            }
        }
        return false;
    }

    // Returns the rater's ID
    public String getID ()
    {
        return myID;
    }

    // This returns a double rating of the item if it is in myRatings.
    public double getRating (String item)
    {
        for (Rating myRating : myRatings)
        {
            if (myRating.getItem().equals(item))
            {
                return myRating.getValue();
            }
        }
        return -1;
    }

    //This returns the number of ratings this Rater has
    public int numRatings()
    {
        return myRatings.size();
    }

    // This return an ArrayList of String representing a list of all
    // the items that this Rater has rated.
    public ArrayList<String> getItemsRated ()
    {
        ArrayList<String> list = new ArrayList<String>();

        for (Rating myRating : myRatings)
        {
            list.add(myRating.getItem());
        }

        return list;
    }
}
