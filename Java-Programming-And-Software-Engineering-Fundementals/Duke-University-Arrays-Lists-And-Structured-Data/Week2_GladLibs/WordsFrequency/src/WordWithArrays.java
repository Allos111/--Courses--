/**
 *  WORD FREQUENCY COUNTER using Array amd StorageResource
 * Find the number of unique words in a file using Array and Storage Resource.
 * The array is not the first choice  because the size of the file is unknown.
 * We'll use the Storage Resource instead.
 */

import edu.duke.*;
import java.util.*;

public class WordWithArrays
{
    private StorageResource myWords;

    public WordWithArrays()
    {
        myWords = new StorageResource();
    }

    /**
     * This method gonna read all the words from the file and put them into
     * our Storage Resource, myWords.
     */
    private void readWords()
    {
        myWords.clear();
        FileResource resource = new FileResource();
        for (String wordInFile : resource.words())
        {
            myWords.add(wordInFile.toLowerCase());
        }
    }

    /**
     * This method is going to look through the array and see if the word that we're
     * passing in matches anything, and if it does, it returns true
     * @param list array of type String containing words,
     * @param word String. We're gonna check if word is in the Array list.
     * @param numStored int where the number of occurrences are stored.
     * @return a boolean
     */
    private boolean contains(String[] list, String word, int numStored)
    {
        for (int k = 0; k < numStored; k++)
        {
            if (list[k].equals(word))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return an int value which is the number of unique words
     */
    private int numberOfUniqueWords()//countDifferentArray()
    {
        int numStored = 0;
        String[] words = new String[myWords.size()];//our String is as big as our Storage Resource.

        for (String wordInFile : myWords.data())
        {
            if (! contains(words, wordInFile, numStored))
            {
                words[numStored] = wordInFile;
                numStored++;
            }
        }
        return numStored;
    }

    private void tester()
    {
        readWords();
        System.out.println("number of words read: " + myWords.size());
        int unique = numberOfUniqueWords();
        System.out.println("array count " + unique);
    }

    public static void main(String[] args)
    {
        WordWithArrays wordCounter = new WordWithArrays();
        wordCounter.tester();
    }
}
