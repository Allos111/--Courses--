package com.willmayala;

/**
 * This class is an efficient way to get information about movies.
 * It stores movie information in a HashMap for fast lookup of movie
 * information given a movie ID. The class also filtering movies based on
 * queries.
 */

import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.FileResource;

public class MovieDatabase 
{
	// this  field maps a movie to a movie ID String to a Movie object with
    // all the information about that movie.
    private static HashMap<String, Movie> ourMovies;

    //this method can be called with the name of the file used to initialized
    //the movie database.
    public static void initialize(String moviefileName)
    {
        if (ourMovies == null)
        {
            ourMovies = new HashMap<String,Movie>();//
            loadMovies(moviefileName);
            //loadMovies("");
        }
    }

    /**
     * this method will load the movie file ratedmoviesfull.csv if no
     * file has been loaded this method is called as a safety check with any
     * of the other public methods to make sure there is movie data in
     * the database.
     */
    private static void initialize()
    {
        if (ourMovies == null) 
        {
            ourMovies = new HashMap<String,Movie>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }	

	// this methods builds the HashMap
    private static void loadMovies(String filename)
    {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list)
        {
            ourMovies.put(m.getID(), m);
        }
    }

    // this returns true if the id is a movie in the database, and false otherwise.
    public static boolean containsID(String id)
    {
        initialize();
        return ourMovies.containsKey(id);
    }

    // Getters
    public static int getYear(String id)
    {
        initialize();
        return ourMovies.get(id).getYear();
    }

    public static String getGenres(String id)
    {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    public static String getTitle(String id)
    {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    public static Movie getMovie(String id)
    {
        initialize();
        return ourMovies.get(id);
    }

    public static String getPoster(String id) 
    {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    public static int getMinutes(String id)
    {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id)
    {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id)
    {
        initialize();
        return ourMovies.get(id).getDirector();
    }
    // end of gettersc

    // this returns the number of moves in the database
    public static int size()
    {
        return ourMovies.size();
    }

    // this returns an ArrayList of type String of movie IDs that match the filtering criteria.
    public static ArrayList<String> filterBy(Filter f) 
    
    {
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }
}

