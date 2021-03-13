package com.willmayala;

/**
 * This class processes movies and rating data and answers
 * questions about them.
 * @author Allos111
 *
 */

import java.util.*;

public class SecondRatings
{
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;
	
	public SecondRatings ()
	{
		// Default constructor
		this("ratedmoviesfull.csv", "ratings.csv");
	}
	
	public SecondRatings(String moviefile, String ratingsfile)
	{
		FirstRatings firstRatings = new FirstRatings();
		myMovies = firstRatings.loadMovies(moviefile);
		myRaters = firstRatings.loadRaters(ratingsfile);
	}
	
	// Return the number of movies that were read in and stored in the ArrayList of type Movie
	public int getMovieSize()
	{
		return myMovies.size();
	}
	
	// Return the number of raters that were read in and stored in the ArrayList of type Rater
	public int getRaterSize()
	{
		return myRaters.size();
	}
	
	/**
	 * This is a helper method
	 * @param id a String representing the movie ID
	 * @param minimalRaters an integer, the number of minimal raters for the movie 
	 * @return a double representing the average movie rating for this ID if there are at least
	 * minimalRaters ratings.
	 */
	private double getAverageByID (String movieId, int minimalRaters)
	{
		double average = 0;
		double total = 0;
		int countRaters = 0;
		 
		for (Rater rater : myRaters)
		{
			if (rater.hasRating(movieId))
			{
				countRaters++;
				total = total + rater.getRating(movieId);
			}
		}
		
		if (countRaters >= minimalRaters)
		{
			average = total / countRaters;
		}
		return average;
	}
	
	/**
	 * This method finds the average rating for every movie that has been rater by at least minimalRaters,
	 * Store each such rating in a rating object in which the movie ID and the average rating are used
	 * in creating the rating object.
	 * @param minimalRaters an integer representing the minimalRaters ratings.
	 * @return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters
	 * Supplying a rater.
	 */
	public  ArrayList<Rating> getAverageRatings (int minimalRaters)
	{
		ArrayList<Rating> avgRatingList = new ArrayList<Rating>();
		for (Movie m : myMovies)
		{
			String movieID = m.getID();
			double avg = getAverageByID(movieID, minimalRaters);
			if (avg > 0.0)
			{
				Rating currRating = new Rating(movieID, avg);
				avgRatingList.add(currRating);
			}
		}
		return avgRatingList;
	}
	
	/**
	 * This method return the title of the movie with that Id.
	 * @param movieID a String representing the ID of the movie.
	 * @return the title of the movie with that ID
	 */
	public String getTitle (String id)
	{
		String title = "";
		for (Movie m : myMovies)
		{
			String movieID = m.getID();
			if (movieID.equals(id))
			{
				title = m.getTitle();
				return title;
			}
		}
		return "The ID provided can not be found";
	}
	
	// Return the movie ID of this movie
	public String getID (String title)
	{
		for (Movie m : myMovies)
		{
			String movieTitle = m.getTitle();
			if (movieTitle.equals(title))
			{
				return m.getID();
			}
		}
		return "The title provided can not be found";
	}
}
