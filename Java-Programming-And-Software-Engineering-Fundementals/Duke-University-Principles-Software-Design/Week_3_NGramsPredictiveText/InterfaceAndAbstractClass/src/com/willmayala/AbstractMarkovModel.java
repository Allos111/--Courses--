package com.willmayala;

import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * @author (Toussaint Will Mayala)
 * @version (May 2020)
 */


public abstract class AbstractMarkovModel implements IMarkovModel
{
    protected String myText;
    protected Random myRandom;
    protected int order;

    public AbstractMarkovModel()
    {
        myRandom = new Random();
    }

    public AbstractMarkovModel(int order)
    {
        myRandom = new Random();
        this.order = order;
    }

    public void setTraining(String s) {
        myText = s.trim();
    }

    public void setMyRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    abstract public String getRandomText(int numChars);

    /**
     * This method should find all the characters from the private variable myText in MarkovOne
     * that follow key and put all these characters into an ArrayList and then return this ArrayList.
     * @param key String.
     * @return an ArrayList of String.
     */
    protected ArrayList<String> getFollows(String key)
    {
        int keyLength = key.length();
        ArrayList<String> follows = new ArrayList<String>();
        for(int i = 0; i < myText.length() -keyLength; i++)
        {
            if(key.equals(myText.substring(i, i+keyLength)))
                follows.add(myText.substring(i+keyLength, i+keyLength+1));
        }
        return follows;
    }

}
