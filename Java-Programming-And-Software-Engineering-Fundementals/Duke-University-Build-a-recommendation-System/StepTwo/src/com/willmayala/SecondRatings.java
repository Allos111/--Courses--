package com.willmayala;

/**
 * SecondRating does many calculations focusing on computing averages
 * on movie ratings.
 * @author Allos111
 * @version (November 2020)
 *
 */

import java.util.*;

public class SecondRatings
{
    private ArrayList<Movie> myMovies; // ArrayList of movies
    private ArrayList<Rater> myRaters; // ArrayList of Raters

    public SecondRatings ()
    {
        // Default constructor
        // this() is used to invoke current class constructor.
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    // Without this second constructor, this class won't compile
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
     * This is just a helper method.
     * @param movieId a String representing the IMDB'ID of the  movie
     * @param minimalRaters an integer representing the number of minimal
     * raters for the movie.
     * @return a double representing the average movie rating for this movieID if there are at least
     * minimalRaters ratings.
     */
    private double getAverageByID (String movieId, int minimalRaters)
    {
        double averageMovieRating = 0.0;
        double totalRatings = 0.0;
        int countRaters = 0;

        for (Rater rater : myRaters)
        {
            if (rater.hasRating(movieId))
            {
                countRaters++;
                totalRatings += rater.getRating(movieId);
            }
        }

        if (countRaters >= minimalRaters)
        {
            averageMovieRating = totalRatings / countRaters; // Compute the average
        }
        return averageMovieRating;
    }

    /**
     * This method finds the average rating for every movie that has been rated by at least minimalRaters,
     * Store each such rating in a rating object in which the movie ID and the average rating are used
     * in creating the rating object.
     * @param minimalRaters an integer representing the minimalRaters ratings.
     * @return an ArrayList of all the Rating objects for movies that have at
     * least the minimal number of raters Supplying a rater.
     */
    public  ArrayList<Rating> getAverageRatings (int minimalRaters)
    {
        ArrayList<Rating> averageRatingList = new ArrayList<Rating>();

        for (Movie movie : myMovies)
        {
            String movieID = movie.getID();
            double avgRating = getAverageByID(movieID, minimalRaters);

            if (avgRating > 0.0)
            {
                Rating currentRating = new Rating(movieID, avgRating);
                averageRatingList.add(currentRating);
            }
        }
        return averageRatingList;
    }

    /**
     * This method return the title of the movie with that ID.
     * @param id a String representing the ID of the movie.
     * @return the title of the movie with that ID
     */
    public String getTitle (String id)
    {
        String title = "";
        for (Movie movie : myMovies)
        {
            String movieID = movie.getID();
            if (movieID.equals(id))
            {
                title = movie.getTitle();
                return title;
            }
        }
        return "THE ID WAS NOT FOUND BECAUSE IT DOESN'T EXIT!";
    }

    // Return the movie ID of this movie
    public String getID (String title)
    {
        for (Movie movie : myMovies)
        {
            String movieTitle = movie.getTitle();
            if (movieTitle.equals(title))
            {
                return movie.getID();
            }
        }
        return "NO SUCH TITLE!";
    }
}
