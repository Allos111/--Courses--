package com.willmayala;

/**
 * This class is used to test the methods created in SecondRatings.
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage
{
    public void printAverageRatings ()
    {
        // Create a SecondRatings object and use the CSV filenames of movies information and ratings information
        SecondRatings sr = new SecondRatings("ratedmovies_short", "ratings_short");
        // Print the number of movies and the number of raters from the two files
        System.out.println ("There are " + sr.getMovieSize() + " movies in the file.");
        System.out.println ("There are  " + sr.getRaterSize() + " ratings in the file.");

        // Code to print a list of movies and their average ratings, for all those movies that have
        // at least a specified number of ratings, sorted by average

        int minimalRaters = 3;
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        Collections.reverse(avgRatingList);

        for (Rating rating : avgRatingList)
        {
            if (rating.getValue() <= 12)
            {
                System.out.println(rating.getValue() + " " + sr.getTitle(rating.getItem()));
            }
        }
        System.out.println("Number of rated movies: " + avgRatingList.size());
    }

    public void getAverageRatingOneMovie()
    {
        int minimalRaters = 0;
        String title = "The Godfather";
        SecondRatings sr = new SecondRatings("ratedmovies_short", "ratings_short");
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
