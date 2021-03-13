package com.willmayala;

/**
 * This class is a more efficient version of the Rater class. It keeps track
 * of one rater and their rato
 */

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater
{
	private String myID; //the Rater's ID
	private HashMap<String, Rating> myRatings;
	//private ArrayList<Rating> myRatings; // the Rater's ratings
	
	public EfficientRater (String id)
	{
		myID = id;
		myRatings = new HashMap<String, Rating>();
		//myRatings = new ArrayList<Rating>();
	}
	
	@Override
	// this creates a new  rating of the item and adds it to myRatings
	public void addRating (String item, double rating)
	{
		myRatings.put(item, new Rating (item, rating));
	}
	
	@Override
	// This prevents from duplicating one rater's rating
	public boolean hasRating (String item)
	{
		return myRatings.containsKey(item);
	}
	
	// Returns the rater's ID
	public String getID ()
	{
		return myID;
	}
	
	@Override
	// This returns a double rating of the item if it is in myRatings.
	public double getRating (String item)
	{
		if (hasRating(item))
		{
			return myRatings.get(item).getValue();
		}
		return -1;
	}
	
	@Override
	//This returns the number of ratings this Rater has
	public int numRatings()
	{
		return myRatings.size();
	}
	
	@Override
	// This return an ArrayList of String representing a list of all the items that this Rater has rated.
	public ArrayList<String> getItemsRated ()
	{
		ArrayList<String> list = new ArrayList<String>();
		for (String item : myRatings.keySet())
		{
			list.add(item);
		}
		return list;
	}
}
