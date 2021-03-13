package com.willmayala;

import java.util.Random;
import java.util.ArrayList;

/**
 * This application generates random text by using two characters to predict the next (At random)
 * @author Duke Software modified by Toussaint Will Mayala.
 * @version 1.0/May 2020.
 */

public class MarkovTwo
{
    private String myText;
    private Random myRandom;

    public MarkovTwo()
    {
        myRandom = new Random();
    }

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    public void setTraining(String s)
    {
        myText = s.trim();
    }

    /**
     * This method generates and returns random text that is numChars long.
     * Remember, for MarkovZero, this class generates each character
     * by randomly choosing a letter from the training text.
     * @param numChars this is the length of the text returned by the
     *                 getRandomText method.
     * @return a String of random text.
     * THIS IS WHERE THE TRAINING TEXT IS USED.
     */
    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() -2);
        String key = myText.substring(index, index + 2);
        sb.append(key);
        for (int k = 0; k < numChars - 2; k++)
        {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0)
            {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }

    /**
     * This method should find all the characters from the private variable myText in MarkovOne
     * that follow key and put all these characters into an ArrayList and then return the ArrayList.
     * @param key String.
     * @return an ArrayList of String.
     */
    public ArrayList<String> getFollows(String key)
    {
        int keyLength = key.length();
        ArrayList<String> follows = new ArrayList<String>();
        for(int i = 0; i < myText.length() - keyLength; i++)
        {
            if(key.equals(myText.substring(i, i+keyLength)))
                follows.add(myText.substring(i+keyLength, i+keyLength + 1));
        }
        return follows;
    }
}
