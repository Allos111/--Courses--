package com.willmayala;

/**
 * This class is a POJO (plain old java object) and mirrors a CSV file for
 * data. Each of its instance represents a movie.
 * @author Allos111
 * An immutable passive data object (PDO) to represent item's data
 */

public final class Movie
{
    private String id;
    private String title;
    private int year;
    private String genres; // one String of one or more genres separated by commas
    private String director;
    private String country;
    private String poster; // a String that is a link to an image of the movie poster
    private int minutes;

    public Movie (String anID, String aTitle, String aYear, String theGenres)
    {
        // Just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
                  String aCountry, String aPoster, int theMinutes)
    {
        // Just in case data file contains extra whitespace
        id = anID.trim();
        title = aTitle.trim();
        year = Integer.parseInt(aYear.trim());
        genres = theGenres;
        director = aDirector;
        country = aCountry;
        poster = aPoster;
        minutes = theMinutes;
    }

    // Returns ID associated with this item
    public String getID ()
    {
        return id;
    }

    // Returns title of this item
    public String getTitle ()
    {
        return title;
    }

    // Returns year in which this item was published
    public int getYear ()
    {
        return year;
    }

    // Returns genres associated with this item
    public String getGenres ()
    {
        return genres;
    }

    // Returns the country in which the movie was produced
    public String getCountry ()
    {
        return country;
    }

    // Returns the director of the movie
    public String getDirector ()
    {
        return director;
    }

    // Returns the Poster of the movie
    public String getPoster ()
    {
        return poster;
    }

    // Returns the duration of the item (in minutes)
    public int getMinutes ()
    {
        return minutes;
    }

    // this method represents movie information as a String so it can easily be printed
    public String toString ()
    {
        String theMovieObject = "Movie [id = " + id + ", title = " + title + ", year = " + year;
        theMovieObject += ", genres = " + genres + "]";
        return theMovieObject;
    }
}

