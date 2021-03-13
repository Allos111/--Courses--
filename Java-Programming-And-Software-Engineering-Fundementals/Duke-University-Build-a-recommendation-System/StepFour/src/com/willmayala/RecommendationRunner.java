package com.willmayala;

import java.util.*;
import java.util.Random;

/**
 * This class displays recommendations according to the rater's ratings
 */
public class RecommendationRunner implements Recommender
{
     public ArrayList<String> getItemsToRate()
     {

         YearAfterFilter year = new YearAfterFilter(1970);
         MinutesFilter minutes = new MinutesFilter(90,200);
         Random randomGenerator = new Random();
         AllFilters all = new AllFilters();
         all.addFilter(year);
         all.addFilter(minutes);

         ArrayList<String> myMovies = MovieDatabase.filterBy(all);
         ArrayList<String> myMoviesRandom = new ArrayList<String>();

         for(int i=0; i<20 ;i++)
         {
              int index = randomGenerator.nextInt(myMovies.size());
              myMoviesRandom.add(myMovies.get(index));
         }
         return myMoviesRandom;
     }
     
     public void printRecommendationsFor(String webRaterID)
     {
         try{
             FourthRatings database = new FourthRatings("ratings.csv");
             MovieDatabase MovDat = new MovieDatabase();
             MovDat.initialize("ratedmoviesfull");
             RaterDatabase RatDat = new RaterDatabase();
             RatDat.initialize("ratings");
             ArrayList<Rating> recommendations = database.getSimilarRatings(webRaterID,5,2);
             ArrayList<Rating> finalRecommendations = new ArrayList<Rating>();
             //These if statements are for printing just 10 movies if the final list is too large or every movie if the list is between 1 and 10.
             if(recommendations.size()==0){
                 System.out.println("Sorry, there is no movie that matches your preferences;)");
                }
             if (recommendations.size()>10){
                for(int i=0;i<10;i++){
                    finalRecommendations.add(recommendations.get(i));
                }
                System.out.println("The system recommends you all these movies:");
                System.out.println("<style>th{color:green}img{border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 150px;}</style>");
                System.out.println("<table>");
                System.out.println("<tr><th>Movie</th><th>Poster</th></tr>");

                for (Rating r : finalRecommendations)
                {
                     System.out.println("<tr>");
                     String movieTitle = MovieDatabase.getTitle(r.getItem()); 
                     String poster = MovieDatabase.getPoster(r.getItem());
                     System.out.println("<th>" + movieTitle + "</th>"+"<th>"+"<img src=" + poster + ">" + "</th>");
                     System.out.println("</tr>");
                }
                System.out.println("</table>");

             }
             else{
                 System.out.println("Based on your ratings, you might like these movies:");
                 System.out.println("<style>th{font-size:150% color:black}#th1{color:blue font-size:175%}img{border: 1px solid #ddd;border-radius: 4px;padding: 5px;width: 150px;}</style>");
                 System.out.println("<table>");
                 System.out.println("<tr><th id="+"th1"+">Movie</th><th id="+"th1"+">Poster</th></tr>");
                 for(Rating r : recommendations){
                     System.out.println("<tr>");
                     String movieTitle = MovieDatabase.getTitle(r.getItem()); 
                     String poster = MovieDatabase.getPoster(r.getItem());
                     System.out.println("<th>"+movieTitle +"</th>"+"<th>"+"<img src="+poster+">"+"</th>");
                      System.out.println("</tr>");
                 }
                 System.out.println("</table>");
                }
        }
        catch(Exception e){
            System.out.println("Please <a link href="+"http://www.dukelearntoprogram.com/capstone/recommender.php?id=MZgRWAHqz6NuLV"+">click here to return</a>.");
        }
    }
    }
