package com.willmayala;

import java.util.Random;

/**
 * This application generates random text based on the distribution of characters
 * in the original text.
 * @author Toussaint Will Mayala.
 * @version May 2020.
 */

public class MarkovZero extends AbstractMarkovModel
{

    public MarkovZero()
    {
        myRandom = new Random();
    }

    /**
     * Using this method will allow us to generate the same random text each time,
     * which will help in testing our program.
     * @param seed an integer parameter.
     */
    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    /**
     * @param s a String that is used to initialize the training test.
     */
    public void setTraining(String s)
    {
        myText = s.trim();
    }

    /**
     * This method generates and returns random text that is numChars long.
     * Remember, for MarkovZero, this class generates each by randomly choosing a letter from the training text.
     * @param numChars an int parameters.
     * @return a String of random text.
     */
    public String getRandomText(int numChars)
    {
        if (myText == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < numChars; k++) {
            int index = myRandom.nextInt(myText.length());
            sb.append(myText.charAt(index));
        }

        return sb.toString();
    }

    public String toString()
    {
        return String.format("MarkovModel of order %d", 0);
    }
}
