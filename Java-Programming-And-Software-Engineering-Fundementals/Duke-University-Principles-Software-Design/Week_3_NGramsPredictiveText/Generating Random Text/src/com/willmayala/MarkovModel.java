package com.willmayala;

import java.util.ArrayList;
import java.util.Random;

/**
 * This application generates random text by using n character to predict the next
 * where n represents an integer.
 * @author Duke Software modified by Toussaint Will Mayala.
 * @version 1.0/May 2020.
 */

public class MarkovModel
{
    private String myText;
    private Random myRandom;
    private int order;

    public MarkovModel(int order)
    {
        myRandom = new Random();
        this.order = order;
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
        int index = myRandom.nextInt(myText.length() -order);
        String key = myText.substring(index, index + order);
        sb.append(key);
        for (int k = 0; k < numChars -order; k++)
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
     * that follow key and put all these characters into an ArrayList and then return this ArrayList.
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
                follows.add(myText.substring(i+keyLength, i+keyLength+1));
        }
        return follows;
    }
}
