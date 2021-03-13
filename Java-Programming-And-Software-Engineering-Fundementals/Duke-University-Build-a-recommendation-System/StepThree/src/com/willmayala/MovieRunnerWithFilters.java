package com.willmayala;

/**
 * This class will be used to to find the average ratings of movies
 * using different filters.
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters 
{
	public void printAverageRatings ()
	{
		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println ("The selected file has " + tr.getRaterSize() + " ratings(Number of raters)");
		MovieDatabase.initialize("ratedmovies_short");

		int numberOfMinimalRaters = 1;
		MovieDatabase.initialize("ratedmovies_short");
		System.out.println ("The selected file has " + MovieDatabase.size() + " movies");
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatings(numberOfMinimalRaters);
		System.out.println(avgRatingList.size() + " rated movies returned");
		
		Collections.sort(avgRatingList);
		
		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
		}
		//System.out.println("avgRatingList size " + avgRatingList.size());
	}

	public void printAverageRatingYear ()
	{
		int year = 2000;
		YearAfterFilter yearAfterFilter = new YearAfterFilter(year);

		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println ("Read data for " + tr.getRaterSize() + " raters");
		MovieDatabase.initialize("ratedmovies_short");

		int numberOfMinimalRaters = 1;
		MovieDatabase.initialize("ratedmovies_short");
		System.out.println ("Read data for " + MovieDatabase.size() + " movies");

		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(numberOfMinimalRaters, yearAfterFilter);
		System.out.println("found " + avgRatingList.size());

		Collections.sort(avgRatingList);

		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
					+ MovieDatabase.getTitle(rating.getItem()));
		}
		//System.out.println("avgRatingList size " + avgRatingList.size());
	}
	
	public void printAverageRatingsByYearAfter ()
	{
		int year = 2000;
		YearAfterFilter yearFilter = new YearAfterFilter(year);
		
		ThirdRatings tr = new ThirdRatings("ratings");
		System.out.println("The number of raters is " + tr.getRaterSize());
		int minimaRaters = 20;
		MovieDatabase.initialize("ratedmoviesfull");
		System.out.println("The movies number is " + MovieDatabase.size());
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimaRaters, yearFilter);
		System.out.println(avgRatingList.size()+ " rated movies returned");
		Collections.sort(avgRatingList);
		
		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + " "
					+ MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByGenre()
	{
		String genre = "Crime";
		GenreFilter genreFilter = new GenreFilter(genre); 

		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println("read data for " + tr.getRaterSize() + " raters");

		int minimalRaters = 1;
		MovieDatabase.initialize("ratedmovies_short");
		System.out.println("read data for " + MovieDatabase.size() + " movies");
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, genreFilter);
		System.out.println("found " + avgRatingList.size());
		Collections.sort(avgRatingList);

		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
			System.out.println(" " + MovieDatabase.getGenres(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByMinutes()
	{
		int minMinutes = 110;
		int maxMinutes = 170;
		MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes); 
		
		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println("read data for " + tr.getRaterSize() + " raters");
		int minimalRaters = 1;
		MovieDatabase.initialize("ratedmovies_short");
		System.out.println("read data for " + MovieDatabase.size() + " movies");
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, minutesFilter);
		System.out.println("found " + avgRatingList.size() + " movies");
		Collections.reverse(avgRatingList);

		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) 
			+ " " + MovieDatabase.getTitle(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByDirectors()
	{
		String director =  "Charles Chaplin,Michael Mann,Spike Jonze";
		//String[] theDirectors = director.split(",");
		DirectorsFilter directorsFilter = new DirectorsFilter(director);
		//Filter dir = new DirectorsFilter("Michael Mann,Spike Jonze");
		
		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println("The number of raters is " + tr.getRaterSize());
		int minimalRaters = 1;
		MovieDatabase.initialize("ratedmovies_short");
		System.out.println("The number of movies is " + MovieDatabase.size());
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, directorsFilter);
		System.out.println(avgRatingList.size() + " rated movies returned");
		Collections.sort(avgRatingList);
		for (Rating rating : avgRatingList)
		{
			if (rating.getValue() > 0) 
			{
				System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
				System.out.println(" " + MovieDatabase.getDirector(rating.getItem()));
			}
		}
	}
	
	public void printAverageRatingsByYearAfterAndGenre()
	{
		String genre = "Romance";
		GenreFilter genreFilter = new GenreFilter(genre); 
		int year = 1980;
		YearAfterFilter yearFilter = new YearAfterFilter(year);
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(yearFilter);
		allFilters.addFilter(genreFilter);
		
		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println("read data for " + tr.getRaterSize() + " raters");

		int minimalRaters = 1;

		MovieDatabase.initialize("ratedmovies_short");
		System.out.println("read data for " + MovieDatabase.size() + " movies");
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
		System.out.println(avgRatingList.size() + " movie(s) matched");
		Collections.sort(avgRatingList);

		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem()) + 
					" " + MovieDatabase.getTitle(rating.getItem()));
			System.out.println(" " + MovieDatabase.getGenres(rating.getItem()));
		}
	}
	
	public void printAverageRatingsByDirectorsAndMinutes()
	{
		int minMinutes  = 30;
		int maxMinutes = 170;
		String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
		MinutesFilter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
		DirectorsFilter directorsFilter = new DirectorsFilter(directors);
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(minutesFilter);
		allFilters.addFilter(directorsFilter);
		
		ThirdRatings tr = new ThirdRatings("ratings_short");
		System.out.println("read data for " + tr.getRaterSize() + " raters");
		int minimalRaters = 1;

		MovieDatabase.initialize("ratedmovies_short");
		System.out.println("read data for " + MovieDatabase.size() + " movies");
		
		ArrayList<Rating> avgRatingList = tr.getAverageRatingsByFilter(minimalRaters, allFilters);
		System.out.println(avgRatingList.size() + " movie(s) matched");
		Collections.sort(avgRatingList);

		for (Rating rating : avgRatingList)
		{
			System.out.println(rating.getValue() + " Time: " + MovieDatabase.getMinutes(rating.getItem()) + 
					" Title: " + MovieDatabase.getTitle(rating.getItem()));
			System.out.println(" Director: " + MovieDatabase.getDirector(rating.getItem()));
		}
	}
}
