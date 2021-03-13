package com.willmayala;

/**
 * Will use this class to calculate the number of movies in the database
 * that have at least a minimal number of ratings and came out in a particular
 * year or later.
 * @author Allos111
 */

public class YearAfterFilter implements Filter
{
	private int myYear;
	
	public YearAfterFilter(int year) 
	{
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) 
	{
		return MovieDatabase.getYear(id) >= myYear;
	}
}
   