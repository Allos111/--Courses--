package com.willmayala;

import edu.duke.*;
import org.w3c.dom.ls.LSOutput;

/**
 *This application is for running our program to generate random text.
 * @author Duke Software modified by Toussaint Will Mayala.
 * @version 1.0/May 2020
 */


public class MarkovRunnerWithInterface
{
    /**
     * This method will work with any Markov object that implement IMarkovModel
     * @param markov of type IMarkovModel.
     * @param text of type String.
     * @param size of type int.
     */
    public void runModel(IMarkovModel markov, String text, int size, int seed)
    {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k = 0; k < 3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }

    /**
     * This method creates one object of the types of Markov models, and calls runModel
     * with it to generate random text.
     */
    public void runMarkov()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        int seedNum = 0;
        st = st.replace('\n', ' ');
        int size = 200;


        System.out.println("========== Markov Zero ==========\n");
        //MarkovZero mz = new MarkovZero();
        IMarkovModel mz = new MarkovZero();
        runModel(mz, st, size, seedNum);
        System.out.println("\n");

        System.out.println("========== Markov One ==========\n");
        //MarkovOne mOne = new MarkovOne();
        IMarkovModel mOne = new MarkovOne();
        runModel(mOne, st, size,seedNum);
        System.out.println("\n");

        System.out.println("========== Markov Model ==========\n");
        //MarkovModel mThree = new MarkovModel(3);
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size,seedNum);
        System.out.println("\n");

        System.out.println("========== Markov Four ==========\n");
        //MarkovFour mFour = new MarkovFour();
        IMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size,seedNum);
        System.out.println("\n");

//        System.out.println("========= Efficient Markov Model ===========");
//        IMarkovModel efficientMarkov = new EfficientMarkovModel(5);
//        runModel(efficientMarkov, st, size, seedNum);
//        System.out.println("\n");

    }

    /**
     * This method formats and prints out the randomly generated text.
     * @param s of type String.
     */
    private void printOut(String s)
    {
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for (int k = 0; k < words.length; k++) {
            System.out.print(words[k] + " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println();
                psize = 0;
            }
        }
        System.out.println("\n----------------------------------");
    }

    public void testHashMap()
    {
        String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int seed = 42;
        int size = 50;
        EfficientMarkovModel efficientMarkovModel = new EfficientMarkovModel(2);
        runModel(efficientMarkovModel,st,size,seed);
    }

    public void compareMethods()
    {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 42;
        int size = 1000;

        double startTime = System.nanoTime();
        MarkovModel mTwo = new MarkovModel(2);
        runModel(mTwo,st,size,seed);
        double endTime = System.nanoTime();
        System.out.println("Running time of MarkovModel is " + (endTime - startTime)/1000000000.0 +
                "seconds\n");

        startTime = System.nanoTime();
        EfficientMarkovModel eModel= new EfficientMarkovModel(2);
        runModel(eModel,st,size,seed);
        endTime = System.nanoTime();
        System.out.println("Running time of MarkovModel is " + (endTime - startTime)/1000000000.0 +
                "seconds");
    }

    public void testQuiz()
    {
        FileResource fileResource = new FileResource();
        String st = fileResource.asString();
        st = st.replace('\n',' ');
        int seed = 615;
        int size = 1000;

        EfficientMarkovModel eModel = new EfficientMarkovModel(5);
        runModel(eModel, st, size, seed);
    }
}
