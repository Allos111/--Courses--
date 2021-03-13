package com.willmayala;

import java.util.Random;
import java.util.ArrayList;

/**
 * This application generates random text by using one character to predict the next (At random)
 * @author Duke Software modified by Toussaint Will Mayala.
 * @version 1.0/May 2020.
 */

public class MarkovOne
{
    private String myText;
    private Random myRandom;

    public MarkovOne()
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

    public String getRandomText(int numChars)
    {
        if (myText == null)
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length() - 1);
        String key = myText.substring(index, index + 1);
        sb.append(key);
        for (int k = 0; k < numChars -1; k++)
        {
            ArrayList<String> follows = getFollows(key);
            if(follows.size() == 0)
            {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
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
        ArrayList<String> follows = new ArrayList<String>();
        for(int i = 0; i < myText.length() -1; i++)
        {
            if(key.equals(myText.substring(i, i+1)))
                follows.add(myText.substring(i+1, i+2));
        }
        return follows;
    }
}
