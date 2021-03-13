package com.willmayala;

import java.util.Random;
/**
 * This application generates random text based on the distribution of characters
 * in the original text (By Using any character at random). This is a model for other Markov classes (All Markov
 * models will use a training text as the basis for generating text at random).
 * @author Duke Software modified by Toussaint Will Mayala
 * @version 1.0
 */

public class MarkovZero
{
    private String myText;
    private Random myRandom;

    public MarkovZero()
    {
        myRandom = new Random();
    }

    /**
     * Using this method will allow us to generate the same random text each time,
     * which will help in testing our program.
     * @param seed of the random number generator.
     */
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    /**
     * This method sets the training text used to generate words at random.
     * @param s a String that is used to initialize the training text.
     */
    public void setTraining(String s)
    {
        myText = s.trim();
    }

    /**
     * This method generates and returns random text that is numChars long.
     * Remember, for MarkovZero, this class generates each character
     * by randomly choosing a letter from the training text.
     * @param numChars an int parameters.
     * @return a String of random text.THE
     * THIS IS WHERE THE TRAINING TEXT IS USED.
     */
    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder(); // this store the random text
        for (int k = 0; k < numChars; k++)
        {
            int index = myRandom.nextInt(myText.length()); //random index created
            sb.append(myText.charAt(index));// random index accessed
        }
        return sb.toString();
    }
}
