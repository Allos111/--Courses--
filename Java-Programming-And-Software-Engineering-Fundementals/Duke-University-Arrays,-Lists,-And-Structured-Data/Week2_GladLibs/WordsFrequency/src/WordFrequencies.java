/**
 * WORD FREQUENCY COUNTER
 * ----------------------
 * Find out and keep track of how many times each word occurs in a file
 * or in URL(web page), and in particular the most frequently occurring word.
 */

import edu.duke.*;
import java.security.PublicKey;
import java.util.*;

public class WordFrequencies
{
    /**
     * Declaring two ArrayList Structures.
     * Instance variables array lists.
     */
    private ArrayList<String> myWords; //Holds String values
    private ArrayList<Integer> myFreqs; //Holds Integer values

    /**
     * This is method which is the constructor,
     * Initializing the two instance variables.
     */
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();//Stores the actual words to be counted
        myFreqs = new ArrayList<Integer>(); //Stores the number of occurrences of the kth value in the instance variable array list myWords
    }

    /**
     * This method counts how many words are in the file.
     * every word is only counted one time. Not a word should be counted more than once.
     */
    private void findUnique()
    {
        FileResource resource = new FileResource();

        //This loop over every word in the file and stores each word in the wordInFile
        for (String wordInFile : resource.words())
        {
            wordInFile = wordInFile.toLowerCase();
            int index = myWords.indexOf(wordInFile);
            if (index == -1) //If the word hasn't been stored yet
            {
                myWords.add(wordInFile);//The word is only stored once
                myFreqs.add(1);
            }
            else //if the word has been stored already
            {
                int value = myFreqs.get(index);//Access the value in myFreqs by the location specifies by index and stored it in value
                myFreqs.set(index, value + 1);//Set the value at index to value+1
            }
        }
    }

    private void tester()
    {
        findUnique();
        System.out.println("# unique words: " + myWords.size());
        //int index = findMax();
        //System.out.println("max word/freq: "+ myWords.get(index)+" "+ myFreqs.get(index));
        for(int k = 0; k < myWords.size(); k++)
        {
            System.out.println(" The word " + "'" + myWords.get(k) + "'" + " appears: " + "\t" + myFreqs.get(k) + " times");
        }
    }

    public int findMax()
    {
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for (int k = 0; k < myFreqs.size(); k++)
        {
            if (myFreqs.get(k) > max)
            {
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args)
    {
        WordFrequencies freqs = new WordFrequencies();
        freqs.tester();
        //int max = freqs.findMax();

    }
}
