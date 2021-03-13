package com.willmayala;

import java.util.*;

public interface Rater 
{
	// this creates a new  rating and adds it to myRatings
	public void addRating (String item, double rating);
		
		
	// This prevents from duplicating one rater's rating
	public boolean hasRating (String item);
		
	// Returns the rater's ID
	public String getID ();
		
	// This returns a double rating of the item if it is in myRatings.
	public double getRating (String item);
		
	//This returns the number of ratings this Rater has
	public int numRatings();
		
	// This return an ArrayList of String representing a list of all the items that this Rater has rated.
	public ArrayList<String> getItemsRated ();
}
