package com.willmayala;
/**
 * MovieRunnerSimilarRatings class contains various tests for the methods
 * from FourthRatings.
 * @ Allos111
 * @ Version: 1.0 (November, 2020)
 */

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings
{
    public void printAverageRatings ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings_short");
        MovieDatabase.initialize("ratedmovies_short");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        int minNumOfRatings = 1;
        ArrayList<Rating> averageRatings = fourthRatings.getAverageRatings(minNumOfRatings);
        System.out.println("There are " + averageRatings.size() + " movies with " +
        minNumOfRatings + " or more rating(s) :");
        
        Collections.sort(averageRatings);

        for (Rating rating : averageRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1990;
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        String genre = "Drama";
        GenreFilter gf = new GenreFilter (genre);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(gf);
        
        int minNumOfRatings = 8;
        ArrayList<Rating> avgRatings = fourthRatings.getAverageRatingsByFilter(minNumOfRatings, af);
        System.out.println("There is(are) " + avgRatings.size() + " movie(s)  in genre of \""
        + genre + "\" that was(were) directed after " + year + " with " + minNumOfRatings 
        + " or more rating(s) :");
        
        Collections.sort(avgRatings);
        for (Rating rating : avgRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getYear(rating.getItem())
            + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre : " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method creates a new FourthRatings object, reads data into the MovieDatabase
     * and RaterDatabase, and then calls getSimilarRatings for a particular
     * rater ID, a number for the top number of similar raters, and a number of
     * minimal rateSimilarRatings, and then lists recommended movies and their
     * similarity ratings.
     */
    public void printSimilarRatings ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        String id = "337";
        int numSimilarTopRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatings(id, numSimilarTopRaters, minimalRaters);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " with " + minimalRaters
        + " or more rating(s). " + numSimilarTopRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a genre
     * filter and then lists recommended movies and their similarity ratings,
     * and for each movie prints the movie and its similarity rating on one
     * line and its genres on a separate line below it.
     */
    public void printSimilarRatingsByGenre ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Mystery";
        GenreFilter gf = new GenreFilter(genre);
         
        String id = "964";
        int numSimilarTopRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarTopRaters, minimalRaters, gf);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre. " + numSimilarTopRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a
     * director filter and then lists recommended movies and their similarity
     * ratings, and for each movie prints the movie and its similarity rating
     * on one line and its directors on a separate line below it.
     */
    public void printSimilarRatingsByDirector ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        DirectorsFilter df = new DirectorsFilter(directors);
         
        String id = "120";
        int numSimilarTopRaters = 10;
        int minimalRaters = 2;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarTopRaters, minimalRaters, df);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that was(were) directed by either of the following directors: "
        + directors + ". " + numSimilarTopRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem()));
            System.out.println("Directed by : " + MovieDatabase.getDirector(rating.getItem()));
        }
    }

    /**
     * his method is similar to printSimilarRatings but also uses a genre
     * filter and a minutes filter and then lists recommended movies and
     * their similarity ratings, and for each movie prints the movie,
     * its minutes, and its similarity rating on one line and its genres on
     * a separate line below it.
     */
    public void printSimilarRatingsByGenreAndMinutes ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        String genre = "Drama";
        GenreFilter gf = new GenreFilter (genre);
        
        int minMin = 80;
        int maxMin = 160;
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(gf);
        af.addFilter(mf);
        
        String id = "168";
        int numSimilarTopRaters = 10;
        int minimalRaters = 3;
        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarTopRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), in \"" + genre + "\" genre, that is(are) between " + minMin
        + " and " + maxMin + " minutes in length. " + numSimilarTopRaters + " closest raters were considered.");
        
        for (Rating rating : similarRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Time: " + MovieDatabase.getMinutes(rating.getItem()));
            System.out.println("Genre: " + MovieDatabase.getGenres(rating.getItem()));
        }
    }

    /**
     * This method is similar to printSimilarRatings but also uses a
     * year after filter and a minutes filter and then lists recommended movies
     * and their similarity ratings, and for each movie prints the movie, its year,
     * its minutes, and its similarity rating on one line.
     */
    public void printSimilarRatingsByYearAfterAndMinutes ()
    {
        FourthRatings fourthRatings = new FourthRatings ("ratings");
        MovieDatabase.initialize("ratedmoviesfull");
        
        System.out.println("Read data for " + RaterDatabase.size() + " raters");
        System.out.println("Read data for " + MovieDatabase.size() + " movies");
        
        int year = 1975;
        YearAfterFilter yaf = new YearAfterFilter (year);
        
        int minMin = 70;
        int maxMin = 200;
        MinutesFilter mf = new MinutesFilter (minMin, maxMin);
        
        AllFilters af = new AllFilters();
        af.addFilter(yaf);
        af.addFilter(mf);
        
        String id = "314";
        int numSimilarTopRaters = 10;
        int minimalRaters = 5;

        ArrayList<Rating> similarRatings = fourthRatings.getSimilarRatingsByFilter
        (id, numSimilarTopRaters, minimalRaters, af);
        System.out.println("There is(are) " + similarRatings.size() + " movie(s) that is(are) " 
        + "recommended for the rater with ID " + id + " and with " + minimalRaters
        + " or more rating(s), that is(are) between " + minMin + " and " + maxMin 
        + " minutes in length and released after year " + year + ". " + numSimilarTopRaters 
        + " closest raters were considered.");
        
        for (Rating rating : similarRatings)
        {
            System.out.println(rating.getValue() + " " + MovieDatabase.getTitle(rating.getItem())
            + " Year: " + MovieDatabase.getYear(rating.getItem()) + " Time: " 
            + MovieDatabase.getMinutes(rating.getItem()));
        }
    }
}
