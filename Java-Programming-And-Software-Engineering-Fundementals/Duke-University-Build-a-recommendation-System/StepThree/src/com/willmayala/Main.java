package com.willmayala;

/**
 * This is the starting point of our application
 */

public class Main 
{
	public static void main(String[] args)
	{
		//MovieRunnerAverage movieRunner = new MovieRunnerAverage();
		MovieRunnerWithFilters movieRunner = new MovieRunnerWithFilters();
		//movieRunner.printAverageRatingsByDirectors();
		//movieRunner.printAverageRatings();
		//movieRunner.printAverageRatingYear();
		//movieRunner.printAverageRatingsByGenre();
		//movieRunner.printAverageRatingsByDirectors();
		//movieRunner.printAverageRatingsByMinutes();
		//movieRunner.printAverageRatingsByYearAfterAndGenre();
		movieRunner.printAverageRatingsByDirectorsAndMinutes();

	}
}