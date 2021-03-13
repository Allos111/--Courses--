package com.willmayala;

/**
 * This is the entry point of the application.
 * This application uses all the provided classes to read in and store
 * information about movies and ratings of the movies by different movie
 * raters to answer simple questions about both movies and ratings.
 */

public class Main
{
    public static void main (String[] args)
    {
        FirstRatings firstRatings = new FirstRatings();
//        System.out.println("testLoadMovies");
//        System.out.println("==============");
//        firstRatings.testLoadMovies();
        System.out.println("\n");
        System.out.println("testLoadsRaters");
        System.out.println("==============");
        firstRatings.testLoadsRaters();
    }
}
