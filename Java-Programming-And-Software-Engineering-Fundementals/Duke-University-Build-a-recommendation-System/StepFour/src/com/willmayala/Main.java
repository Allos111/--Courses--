package com.willmayala;

public class Main
{
    public static void main(String[] args)
    {
        MovieRunnerSimilarRatings movieRunner = new MovieRunnerSimilarRatings();
        RecommendationRunner recommendationRunner = new RecommendationRunner();
        //movieRunner.printAverageRatings();
        //movieRunner.printSimilarRatings ();
        //movieRunner.printSimilarRatingsByGenre();
        //movieRunner.printSimilarRatingsByDirector();
        //movieRunner.printSimilarRatingsByGenreAndMinutes();
        //movieRunner.printSimilarRatingsByYearAfterAndMinutes();
        recommendationRunner.printRecommendationsFor("1098");
    }
}
