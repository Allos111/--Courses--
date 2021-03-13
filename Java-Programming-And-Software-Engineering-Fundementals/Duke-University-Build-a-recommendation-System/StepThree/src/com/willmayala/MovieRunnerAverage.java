package com.willmayala;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage 
{
	public void printAverageRatings ()
	{
		SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings");
		// Print the number of movies and the number of raters
		System.out.println ("The selected file has " + sr.getMovieSize() + " movies.");
		System.out.println ("The selected file has " + sr.getRaterSize() + " ratings");
		
		// Code to print a list of movies and their average ratings, for all those movies that have 
		// at least a specified number of ratings, sorted by average
		int minimalRaters = 12;
		ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
		Collections.sort(avgRatingList);
		
		for (Rating rating : avgRatingList)
		{
			if (rating.getValue() <= 12)
			{
				System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
			}
		}
		System.out.println("avgRatingList size " + avgRatingList.size());
	}
	
	public void getAverageRatingOneMovie()
	{
		int minimalRaters = 0;
		String title = "The Maze Runner";
		SecondRatings sr = new SecondRatings("ratedMoviesfull", "ratings");
		String movieId = sr.getID(title);
		ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);

		for (Rating rating : avgRatingList)
		{
			if (rating.getItem().equals(movieId))
			{
				System.out.println(title + " " + rating.getValue());
			}
		}
	}
}
