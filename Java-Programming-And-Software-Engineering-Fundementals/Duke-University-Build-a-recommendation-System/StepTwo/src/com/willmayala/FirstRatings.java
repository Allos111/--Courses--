package com.willmayala;

/**
 * This class process the movie and ratings data and answers questions about them.
 * @author Allos111
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings
{
    /**
     * This method processes every record from the CSV file whose name is fileName,
     * a file of movie information
     * @param fileName name of the CSV file.
     * @return an ArrayList of type Movie with all the movie data from the file
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
            Movie theMovie = new Movie(anID, aTitle, aYear, theGenres, aDirector, aCountry, aPoster, theMinutes);
            //theMovie.toString();
            movieData.add(theMovie);
        }
        return movieData;

    }

    public void testLoadMovies ()
    {
        ArrayList<Movie> movieData = loadMovies("ratedmoviesfull"); // Call the loadMovies method
        //ArrayList<Movie> movieData = loadMovies("ratedmovies"); // Call the loadMovies method

        System.out.println("There are " + movieData.size() + " movies in this file");
        System.out.println("Movie objects: " + movieData);

        int comedyCount = 0; // A count of movies of genre comedy
        int timeAmountInMinutes = 0; // A count of movies that are more than 150 minutes long

        for (Movie movie : movieData)
        {
            if (movie.getGenres().contains("Comedy"))
            {
                comedyCount += 1;
            }

            if(movie.getMinutes() > 150)
            {
                timeAmountInMinutes++;
            }
        }
        System.out.println("There is " + comedyCount + " Movie of genre comedy in the file");
        System.out.println (timeAmountInMinutes + " are more than 150 minutes long in the file");

        // Using the HashMap to count and store the maximum number of movies by any director, and who the directors are that directed that many movies
        HashMap<String, Integer> moviesDirectors = new HashMap<String, Integer>(); // To store the director and the amount of movies he has
        for (Movie movie : movieData)
        {
            String[] directors = movie.getDirector().split(",");
            //System.out.println("String[] directors = " + Arrays.toString(directors));

            for (String director : directors)
            {
                director = director.trim();
                if (! moviesDirectors.containsKey(director))
                {
                    moviesDirectors.put(director, 1);
                }else {
                    moviesDirectors.put(director, moviesDirectors.get(director) + 1);
                }
            }
        }

        // Count and store the number of movies per directors (for each director)
        int moviesMax = 0;
        for (String director : moviesDirectors.keySet())
        {
            if (moviesDirectors.get(director) > moviesMax)
            {
                moviesMax = moviesDirectors.get(director);
            }
        }

        // Create an ArrayList with directors from the list that directed max number of movies
        ArrayList<String>  listOfDirectors = new ArrayList<String>();
        for (String director : moviesDirectors.keySet())
        {
            if (moviesDirectors.get(director) == moviesMax)
            {
                listOfDirectors.add(director);
            }
        }
        System.out.println ("Max number of movies directed by one director: " + moviesMax);
        System.out.println ("There are "+ listOfDirectors.size() +" directors who directed that many movies: " + listOfDirectors);
    }

    /**
     * This method should process every record from the CSV file whose name is filename.
     * A file of raters and their ratings.
     * @param fileName of type String.
     * @return return an ArrayList of type Rater with all the rater data (ratings) from the file.
     */
    public ArrayList<Rater> loadRaters (String fileName)
    {

        FileResource fr = new FileResource("data/" + fileName + ".csv");
        CSVParser parser = fr.getCSVParser();

        ArrayList<Rater> dataOfRater = new ArrayList<Rater>();
        ArrayList<String> iDsList = new ArrayList<String>();

        for (CSVRecord record : parser)
        {
            String currRaterID= record.get(0);
            String currIMDB = record.get(1);
            double currRatingMovie = Double.parseDouble(record.get(2));

            if (!iDsList.contains(currRaterID))
            {
                Rater currRater = new Rater(currRaterID);
                dataOfRater.add(currRater);
                currRater.addRating(currIMDB, currRatingMovie);
            }
            else {
                for (int k=0; k < dataOfRater.size(); k++)
                {
                    if (dataOfRater.get(k).getID().equals(currRaterID))
                    {
                        dataOfRater.get(k).addRating(currIMDB, currRatingMovie);
                    }

                }
            }
            iDsList.add(currRaterID);
        }
        return dataOfRater;
    }

    public void testLoadsRaters ()
    {
        ArrayList<Rater> raterData = loadRaters("ratings"); // Call the loadRaters() on fileName

        //System.out.println("The total number of Raters is " + raterData.size());// print the total number of raters.

        // For each rater, print the rater's ID and the number of ratings they did on one line,
        // followed by each rating (both the movie ID and the rating given) on a separate line.
        for (Rater rater : raterData)
        {
            ArrayList<String> itemsRated = rater.getItemsRated();
            //System.out.println("the rater's ID is " + rater.getID() + " and this rater has "
            // + rater.numRatings() + " rating(s)");
            for (int k=0; k < itemsRated.size(); k++)
            {
                String iDMovie = itemsRated.get(k);
                double ratingOfMovie = rater.getRating(iDMovie);
                //System.out.println("Movie ID = " + iDMovie + ", rating given : " + ratingOfMovie);
            }
        }

        HashMap <String, HashMap<String, Double>> hashMap = new HashMap<String, HashMap<String, Double>>();

        for (Rater rater : raterData)// iterate through the raterData
        {
            HashMap<String, Double> theRatings = new HashMap<String, Double>();
            ArrayList<String> itemsRated = rater.getItemsRated();

            for (int k=0; k < itemsRated.size(); k++)
            {
                String iDMovie = itemsRated.get(k); // Get the ID of each rated item
                double ratingOfMovie = rater.getRating(iDMovie); // Get the rating of the rated movie

                theRatings.put(iDMovie, ratingOfMovie);
            }
            hashMap.put(rater.getID(), theRatings);
        }

        // Code to find the number of ratings for a particular specified in the code.
        String raterID = "193"; //rater_id
        int raterTotalRatings = hashMap.get(raterID).size();
        System.out.println("Number of ratings for rater with ID number is " + raterID + " = " + raterTotalRatings);

        // Code to find the maximum number of rating by any rater. Determine how many raters
        // have this maximum number of ratings and who those raters are.

        int maxRatings = 0;
        for (String key : hashMap.keySet())
        {
            int currentAmountOfRatings = hashMap.get(key).size();

            if (currentAmountOfRatings > maxRatings)
            {
                maxRatings = currentAmountOfRatings;
            }
        }
        System.out.println("Maximum number of ratings by any rater is " + maxRatings);

        ArrayList<String> raterWithMaxNumOfRatings = new ArrayList<String>();
        for (String key : hashMap.keySet())
        {
            int currentAmountOfRatings = hashMap.get(key).size();

            if (maxRatings == currentAmountOfRatings)
            {
                raterWithMaxNumOfRatings.add(key);
            }
        }
        System.out.println("and rater with ID " + raterWithMaxNumOfRatings + " has that max number of ratings.");

        // Code to find the number of ratings a particular movie has
        String movieID = "1798709";
        int numOfRatings = 0;
        for (String key : hashMap.keySet())
        {
            if (hashMap.get(key).containsKey(movieID))
            {
                numOfRatings += 1;
            }
        }
        System.out.println("The movie with the ID " + movieID + " has " + numOfRatings + " ratings");

        // Code to determine how many have been rated by all these raters
        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for (String key : hashMap.keySet())
        {
            for (String currentMovieID : hashMap.get(key).keySet())
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
