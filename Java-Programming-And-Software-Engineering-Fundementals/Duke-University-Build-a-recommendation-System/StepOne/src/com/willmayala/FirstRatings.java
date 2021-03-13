package com.willmayala;

/**
 *
 * This class processes movies and ratings data and answers questions about them.
 * @author Allos111
 *
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings
{
    /**
     * The method below processes every record from the CSV file whose name is fileName,
     * a file containing movie's information
     * @param fileName name of the CSV file.
     * @return an ArrayList of type Movie with all the movie data from the file,
     * fileName.
     */

    public ArrayList<Movie>loadMovies (String fileName)
    {
        FileResource fr = new FileResource("data/" + fileName + ".csv");
        CSVParser parser = fr.getCSVParser();

        ArrayList<Movie> movieData = new ArrayList<Movie>();

        for (CSVRecord record : parser)
        {
            String anID = record.get("id");
            String aTitle = record.get("title");
            String aYear = record.get("year");
            String theGenres = record.get("genre");
            String aDirector = record.get("director");
            String aCountry = record.get("country");
            String aPoster = record.get("poster");
            int theMinutes = Integer.parseInt(record.get("minutes"));
            Movie theMovie = new Movie(anID, aTitle, aYear, theGenres,
                    aDirector, aCountry, aPoster, theMinutes);
            //theMovie.toString();
            movieData.add(theMovie);
        }
        return movieData;

    }

    /**
     * This is a tester method
     */
    public void testLoadMovies ()
    {
        //ArrayList<Movie> movieData = loadMovies("ratedmoviesfull");
        ArrayList<Movie> movieData = loadMovies("ratedmovies_short2");

        System.out.println("There are " + movieData.size() + " movies in this file.");
        System.out.println("List of movies that are in this file: " + movieData);

        int comedyGenreCount = 0;
        int movieLengthInMinutes = 0;

        for (Movie movie : movieData)
        {
            if (movie.getGenres().contains("Comedy"))
            {
                comedyGenreCount += 1;
            }

            if(movie.getMinutes() > 150)
            {
                movieLengthInMinutes++;
            }
        }
        System.out.println("There are " + comedyGenreCount + " Movie of genre comedy in the file.");
        System.out.println (movieLengthInMinutes + " are more than 150 minutes long in the file.");

        // Code to determine the maximum number of movies by any director using an HashMap (To store the director and his movie's count)
        HashMap<String, Integer> movieDirectors = new HashMap<String, Integer>();

        for (Movie movie : movieData)
        {
            String[] directors = movie.getDirector().split(", ");

            for (String director : directors)
            {
                director = director.trim();
                if (! movieDirectors.containsKey(director))
                {
                    movieDirectors.put(director, 1);
                }else {
                    movieDirectors.put(director, movieDirectors.get(director) + 1);
                }
            }
            // End of Code to determine the maximum number of  movie by any director.
        }

        // Code to count the number of movies per director
        int maxMoviesPerDirector = 0;

        for (String director : movieDirectors.keySet())
        {
            if (movieDirectors.get(director) > maxMoviesPerDirector)
            {
                maxMoviesPerDirector = movieDirectors.get(director);
            }
        }

        // Create an ArrayList with directors from the list that directed max number of movies
        ArrayList<String>  listOfDirectors = new ArrayList<String>();
        for (String director : movieDirectors.keySet())
        {
            if (movieDirectors.get(director) == maxMoviesPerDirector)
            {
                listOfDirectors.add(director);
            }
        }
        System.out.println ("The maximum number of movies directed by one director is: " + maxMoviesPerDirector);
        System.out.println ("There are "+ listOfDirectors.size() + " directors that directed one such movie.");
        System.out.println("Here is a list of the directors: " + listOfDirectors);
        // End of code to count the number of movies per director
    }

    /**
     * This method should process every record from the CSV file whose name is filename.
     * A file of raters and their ratings.
     * @param fileName of type String.
     * @return an ArrayList of type Rater with all the rater data
     * (Rater's ratings) from the file.
     */
    public ArrayList<Rater> loadRaters (String fileName)
    {
        FileResource fr = new FileResource("data/" + fileName + ".csv");
        CSVParser parser = fr.getCSVParser();
        
        ArrayList<String> listOfRatersIDs = new ArrayList<String>(); // Contain raters' IDs from the file fileName
        ArrayList<Rater> ratersData = new ArrayList<Rater>(); //Rater's ratings
        

        for (CSVRecord record : parser)
        {
            String currentRaterID= record.get(0); //Rater's ID from the file fileName
            String currIMDB = record.get(1); // Movie's ID from the file fileName
            double currRatingMovie = Double.parseDouble(record.get(2)); // Rater's rating

            if (!listOfRatersIDs.contains(currentRaterID))
            {
                //Rater currRater = new Rater(currentRaterID);
                Rater currRater = new Rater(currentRaterID);
                ratersData.add(currRater);
                currRater.addRating(currIMDB, currRatingMovie);
            }
            else {
                for (Rater datum : ratersData)
                {
                    if (datum.getID().equals(currentRaterID))
                    {
                        datum.addRating(currIMDB, currRatingMovie);
                    }

                }
                for (Rater ratersDatum : ratersData)
                {
                    if (ratersDatum.getID().equals(currentRaterID))
                    {
                        ratersDatum.addRating(currIMDB, currRatingMovie);
                    }

                }
            }
            listOfRatersIDs.add(currentRaterID);
        }
        return ratersData;
    }

    public void testLoadsRaters ()
    {
        // A call to the loadRaters on the file fileName
        ArrayList<Rater> raterData = loadRaters("ratings_short");

        // print the total number of raters
        System.out.println("There are " + raterData.size() + " raters in the file.");

        // Code to print the rater's ID and number of ratings they made
        // followed each rating (both the movie ID and the rating given)
        for (Rater rater : raterData)
        {
            // For each rater, print the rater's ID and the number of ratings they made on one line,
            // followed by each rating (both the movie ID and the rating given) on a separate line.
            ArrayList<String> itemsRated = rater.getItemsRated();
            //System.out.println("the rater's ID is " + rater.getID() + " and this rater has "
            //+ rater.numRatings() + " rating(s)");

            for (String moviesID : itemsRated)
            {
                double ratingOfMovie = rater.getRating(moviesID);
                System.out.println("Movie ID = " + moviesID + ", rating given : " + ratingOfMovie);
            }
        }

        // Code to find the number of ratings for a particular rater specified in the code.
        HashMap <String, HashMap<String, Double>> hashMapWithRatersData;
        hashMapWithRatersData = new HashMap<String, HashMap<String, Double>>();

        // iterate through the raterData
        for (Rater rater : raterData)
        {
            HashMap<String, Double> theRatings = new HashMap<String, Double>(); // to hold one rater and their ratings
            ArrayList<String> itemsRated = rater.getItemsRated(); // to hold items rated by the rater

            for (int k=0; k < itemsRated.size(); k++)
            {
                String moviesID = itemsRated.get(k); // Get the ID of each rated movie
                double ratingOfMovie = rater.getRating(moviesID); // Get the rating of the rated movie

                theRatings.put(moviesID, ratingOfMovie);
            }
            hashMapWithRatersData.put(rater.getID(), theRatings);
        }

        String raterID = "2"; // ID of the specified rater
        int raterTotalRatings = hashMapWithRatersData.get(raterID).size();
        System.out.println("The total number of ratings for rater with the ID number " + raterID + " is " + raterTotalRatings);
        // End of code to find the number of ratings for a particular rater specified in the code.

        // Code to find the maximum number of rating by any rater. Determine how many raters
        // have this maximum number of ratings and who those raters are.

        int maxNumOfRatingByAnyRater = 0;

        for (String key : hashMapWithRatersData.keySet())
        {
            int currentAmountOfRatings = hashMapWithRatersData.get(key).size();

            if (currentAmountOfRatings > maxNumOfRatingByAnyRater)
            {
                maxNumOfRatingByAnyRater = currentAmountOfRatings;
            }
        }
        System.out.println("The maximum number of ratings by any rater is " + maxNumOfRatingByAnyRater);

        ArrayList<String> raterWithMaxNumOfRatings = new ArrayList<String>();
        for (String key : hashMapWithRatersData.keySet())
        {
            int currentAmountOfRatings = hashMapWithRatersData.get(key).size();

            if (maxNumOfRatingByAnyRater == currentAmountOfRatings)
            {
                raterWithMaxNumOfRatings.add(key);
            }
        }
        System.out.println("and rater with ID " + raterWithMaxNumOfRatings + " has that max number of ratings.");

        // Code to find the number of ratings a particular movie has
        String movieID = "1798709";
        int numOfRatings = 0;

        for (String theMovieID : hashMapWithRatersData.keySet())
        {
            if (hashMapWithRatersData.get(theMovieID).containsKey(movieID))
            {
                numOfRatings += 1;
            }
        }
        System.out.println("The movie with the ID " + movieID + " has " + numOfRatings + " ratings");

        // Code to determine how many have been rated by all these raters
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for (String key : hashMapWithRatersData.keySet())
        {
            for (String currentMovieID : hashMapWithRatersData.get(key).keySet())
            {
                if (! uniqueMovies.contains(currentMovieID))
                {
                    uniqueMovies.add(currentMovieID);
                }
            }
        }
        System.out.println("The total number of unique movies that have been rated  is " + uniqueMovies.size());
    }
}
