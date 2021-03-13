package com.willmayala;

import java.util.ArrayList;

public class ThirdRatings
{
	private ArrayList<Rater> myRaters; // Store an ArrayList of raters.
	
	public ThirdRatings ()
	{
		// Default constructor
		this("ratings.csv");
	}
	
	public ThirdRatings(String ratingsfile)
	{
		FirstRatings firstRatings = new FirstRatings();
		myRaters = firstRatings.loadRaters(ratingsfile);
	}
	
	// Return the number of raters that were read in and stored in the ArrayList of type Rater
	public int getRaterSize()
	{
		return myRaters.size();
	}
	
	/**
	 * This is a helper method
	 * @param movieId a String representing the movie ID
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
	 * This method finds the average rating for every movie that has been rated by at least minimalRaters,
	 * Store each such rating in a rating object in which the movie ID and the average rating are used
	 * in creating the rating object.
	 * @param minimalRaters an integer representing the minimalRaters ratings.
	 * @return an ArrayList of all the Rating objects for movies that have at least the minimal number of raters
	 * Supplying a rater.
	 */
	public  ArrayList<Rating> getAverageRatings (int minimalRaters)
	{
		ArrayList<Rating> averageRatingList = new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		for (String id : movies)
		{
			String movieID = id;
			double avg = getAverageByID(movieID, minimalRaters);
			if (avg > 0.0)
			{
				Rating currRating = new Rating(movieID, avg);
				averageRatingList.add(currRating);
			}
		}
		return averageRatingList;
	}
	
	/**
	 * This is a helper method
	 * @param minimalRaters an integer representing the minimum number of rating a movie must have
	 * @param filterCriteria of type Filter representing a given criteria.
	 * @return an ArrayList of type Rating of all the movies that have at least minimalRaters 
	 * and satisfies the filter criteria. 
	 */
	public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria)
	{
		ArrayList<Rating> minimalRatersAndSatisfyFilterCriteria = new ArrayList<Rating>();
		ArrayList<String> fiteredMoviesIDs = MovieDatabase.filterBy(filterCriteria);

		for (String movieId : fiteredMoviesIDs)
		{
			if (getAverageByID(movieId, minimalRaters) > 0.0)
			{
				Rating rating = new Rating(movieId, getAverageByID(movieId,minimalRaters));
				minimalRatersAndSatisfyFilterCriteria.add(rating);
			}
		}
		return minimalRatersAndSatisfyFilterCriteria;
	}
	
}
