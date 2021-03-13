package com.willmayala;

/**
 * This class mirrors CSV file for data. Each of its instance represents a movie.
 * @author Allos111
 * This class is an immutable passive data object (PDO) to represent item data
 */

public final class Movie 
{
	// instance fields
	private String id;
	private String title;
	private int year;
	private String genres;
	private String director;
	private String country;
	private String poster;
	private int minutes;
	
	public Movie (String anID, String aTitle, String aYear, String theGenres)
	{
		// Just in case data file contains extra whitespace
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
	}
	
	public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
			String aCountry, String aPoster, int theMinutes)
	{
		// Just in case data file contains extra whitespace
		id = anID.trim();
		title = aTitle.trim();
		year = Integer.parseInt(aYear.trim());
		genres = theGenres;
		director = aDirector;
		country = aCountry;
		poster = aPoster;
		minutes = theMinutes;
	}
	
	// Returns ID associated with this item
	public String getID ()
	{
		return id;
	}
	
	// Returns title of this item
	public String getTitle ()
	{
		return title;
	}
	
	// Returns year in which this item was published
	public int getYear ()
	{
		return year;
	}
	
	// Returns genres associated with this item
	public String getGenres ()
	{
		return genres;
	}
	
	// Returns the country in which the movie was produced
	public String getCountry ()
	{
		return country;
	}
	
	// Returns the director of the movie
	public String getDirector ()
	{
		return director;
	}
	
	// Returns the Poster of the movie
	public String getPoster ()
	{
		return poster;
	}
	
	// Returns the duration of the item (in minutes)
	public int getMinutes ()
	{
		return minutes;
	}
	
	// Returns a String containing all the informations of the item
	public String toString ()
	{
		String result = "Movie [id = " + id + ", title = " + title + ", year = " + year;
		result += ", genres = " + genres + "]";
		return result;
	}
}

