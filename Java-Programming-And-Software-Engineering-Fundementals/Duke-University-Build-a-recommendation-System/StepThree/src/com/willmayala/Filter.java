package com.willmayala;

/**
 * This interface has only one signature for the method satisfies.
 * Any filters that implements this interface must also have this method.
 * @author Allos111
 */
public interface Filter 
{
	/**
	 * @param id a String that represents a movie ID
	 * @return a true if the movie satisfies the criteria of all the filters in the filers ArrayList.
	 * this method returns false otherwise.
	 */
	public boolean satisfies(String id);
}
