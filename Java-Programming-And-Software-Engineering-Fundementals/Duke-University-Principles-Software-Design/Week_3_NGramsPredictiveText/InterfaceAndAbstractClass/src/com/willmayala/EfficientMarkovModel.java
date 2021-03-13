package com.willmayala;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * It was noted that the getRandomText method is inefficient. This application
 * solves that problem. This class builds an HashMap to calculate the follows
 * ArrayLists for each possible substring only once, and then uses the HashMap
 * to look at the list of characters following when it is needed.
 */

public class EfficientMarkovModel extends AbstractMarkovModel
{
    private HashMap<String, ArrayList<String>> myMap;

    public EfficientMarkovModel(int order)
    {
        super(order);
        myMap = new HashMap<String, ArrayList<String>>();
    }

    public void setRandom(int seed)
    {
        myRandom = new Random(seed);
    }

    @Override
    public void setTraining(String s)
    {
        super.setTraining(s);
        buildMap();
        printHashMapInfo();
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
        for (int k = 0; k < numChars - order; k++)
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
     * This method builds the HashMap(With handling the case where here may not be a follow character.
     * If that key is not in the HashMap yet, then it should be put in mapped to an empty ArrayList)
     */
    private void buildMap()
    {
        for(int i = 0; i < myText.length() - (order - 1); i++)
        {
            String current = myText.substring(i, i+order);
            String follow = "";

            if(i+order < myText.length())
                follow = myText.substring(i+order, i+order+1);

            if(myMap.containsKey((current)))
            {
                myMap.get(current).add(follow);
            }
            else{
                ArrayList<String> list = new ArrayList<String>();
                list.add(follow);
                myMap.put(current, list);
            }
        }
    }

    @Override
    public ArrayList<String> getFollows(String key)
    {
        return myMap.get(key);
    }

    /**
     * This method print information about the HashMap.
     */
    public void printHashMapInfo()
    {
        System.out.println("it has " + myMap.size() + " keys in the HashMap");
        int maxSize = 0;
        for(String key : myMap.keySet())
        {
            maxSize = Math.max(maxSize, myMap.get(key).size());
//            System.out.println("Key:\t[%s]\tvalues: " + key);
//            System.out.println(myMap.get(key));
        }

        System.out.println("The maximum number of keys following a key is " + maxSize);
        ArrayList<String> keys = new ArrayList<String>();
        for(String key : myMap.keySet())
        {
            if(myMap.get(key).size() == maxSize)
            {
                keys.add(key);
            }
        }
        //System.out.println("Keys that have the largest ArrayList are: " + keys);
    }

    @Override
    public String toString()
    {
        return String.format("This is the EfficientMarkovModel Class of order %d", order);
    }
}
