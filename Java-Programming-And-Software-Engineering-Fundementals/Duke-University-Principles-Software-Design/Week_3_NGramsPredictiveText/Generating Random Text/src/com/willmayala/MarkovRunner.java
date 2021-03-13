package com.willmayala;

/**
 * Write a description of class MarkovRunner here.
 * @author Duke Software and modifed by Toussaint Will Mayala.
 * @version 1.0/May 2020
 */ 

import edu.duke.*;

/**
 * This method reads in a file the user chooses, creates a MarkovZero object, and then generates
 * three sets of randomly generated text using the file read in to choose the random character from.
 * All the other classes are executed from this class.
 */
public class MarkovRunner
{
    /**
     * Ransom Text generator for runMarkovZero
     */
    public void runMarkovZero()
    {
        System.out.println("Markov Zero");
        System.out.println("===========");
        FileResource fr = new FileResource();
        String st = fr.asString();
        //String st = "This is a test yes a test";
        st = st.replace('\n', ' '); //replace each line character with a space
        MarkovZero markov = new MarkovZero();
        markov.setTraining(st);
        markov.setRandom(1024);
        for (int k = 0; k < 3; k++)
        {
            //markov.setRandom(18);
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * This is the execution point for all the classes of the application.
     */
    public void runMarkovOne()
    {
        System.out.println("Markov One");
        System.out.println("==========");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        markov.setRandom(365);
        for (int k = 0; k < 3; k++)
        {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovTwo()
    {
        System.out.println("Markov Two");
        System.out.println("==========");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        //st = "This is a test yes a test";
        MarkovTwo markov = new MarkovTwo();
        markov.setTraining(st);
        markov.setRandom(273);
        for (int k = 0; k < 3; k++)
        {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovFour()
    {
        System.out.println("Markov Four");
        System.out.println("===========");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovFour markov = new MarkovFour();
        markov.setTraining(st);
        markov.setRandom(715);
        for (int k = 0; k < 3; k++)
        {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    public void runMarkovModel()
    {
        System.out.println("Markov Model");
        System.out.println("===========");
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovModel markov = new MarkovModel(7);
        markov.setRandom(953);
        markov.setTraining(st);

        for (int k = 0; k < 3; k++)
        {
            String text = markov.getRandomText(500);
            printOut(text);
        }
    }

    /**
     * This method is called by runMarkovZero to print out the random text that was generated
     * with around 60 characters per line.
     * @param s String.
     */
    private void printOut(String s)
    {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++)
        {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60)
            {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }
}
