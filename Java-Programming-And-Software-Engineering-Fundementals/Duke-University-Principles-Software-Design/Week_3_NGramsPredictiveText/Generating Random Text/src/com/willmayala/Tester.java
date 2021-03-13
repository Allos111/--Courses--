package com.willmayala;

/**
 * This class is for testing purpose.
 */

import java.util.ArrayList;
import edu.duke.*;

public class Tester
{
    /**
     * This method should create a MarkovOne object
     */
    public void testGetFollows()
    {
//        FileResource fr = new FileResource();
//        String st = fr.asString();
        //st = st.replace('\n', ' ');
        MarkovOne markovOne = new MarkovOne();
        MarkovZero makovZero = new MarkovZero();
        MarkovTwo markovTwo = new MarkovTwo();
        String st = "this is a test yes this is a test and the test.";
        //markovOne.setRandom(150);
        markovTwo.setRandom(150);
        //markovOne.setTraining(st);
        markovTwo.setTraining(st);
        //ArrayList<String> follows = markovOne.getFollows("t");
        ArrayList <String> follows = markovTwo.getFollows("th");
        System.out.println(st);
        System.out.print("follows key: " + follows + "\nfollows size: " +
                follows.size());

    }

    public void testGetFollowsWithFile()
    {
        FileResource fileResource = new FileResource();
        String st = fileResource.asString();
        st = st.replace('\n', ' ');
        MarkovModel markovOne = new MarkovModel(1);
        markovOne.setTraining(st);
        markovOne.setRandom(150);
        ArrayList<String> follows = markovOne.getFollows("he");
        System.out.println("Size: " + follows.size());

    }
}
