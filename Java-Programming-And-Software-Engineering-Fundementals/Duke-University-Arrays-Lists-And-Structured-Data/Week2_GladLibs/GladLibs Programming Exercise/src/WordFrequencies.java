/**
 * This program will determine the word that occurs the most often
 * in a file. If more than one word occurs as the most often,
 * then return the first such word found.
 * Objective: DO more practice and understand the ArrayList class.
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    /**
     * Declaration of two instance variables
     * The ArrayList String myWords will contain all the words found in the files, and
     * the ArrayList Integer myFreqs, gonna keep the number of occurrences every word appear
     * in the entire file.
     */
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    /**
     * This method which is the constructor going to initialize the instance variables.
     */
    public WordFrequencies()
    {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /**
     * This method should first clear both myWords and myFreqs, using the .clear() method.
     * Then it selects a file and then iterates over every word in the file,
     * putting the unique words found into myWords. For each word in the kth position of myWords,
     * it puts the count of how many times that word occurs from the selected file
     * into the kth position of myFreqs
     */
    private void findUnique()
    {
        myWords.clear();
        myFreqs.clear();

        FileResource myFile = new FileResource();

        for (String wordInFile : myFile.words())
        {
            wordInFile = wordInFile.toLowerCase();
            int indexOfWordInFile = myWords.indexOf(wordInFile);

            if (indexOfWordInFile == -1)
            {
                myWords.add(wordInFile);
                myFreqs.add(1);
            }
            else
            {
                int value = myFreqs.get(indexOfWordInFile);
                myFreqs.set(indexOfWordInFile, value + 1);
            }
        }
    }

    /**
     * This method should call findUnique. Then print out the number of unique words,
     * and for each unique word, print the frequency of each word and the word.
     */
    private void tester()
    {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());

        /* for each word, print its frequency */
        for(int k = 0; k < myWords.size(); k++)
        {
            System.out.println(myFreqs.get(k) + "\t" +  myWords.get(k));
        }
        /* word that occurs the most in the file and its count */
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " +
                myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }

    /**
     * This method returns an int that is the index location of the largest value
     * in myFreqs. If there is a tie, then return the first such value.
     * @return an int that is the index location of the largest value in myFreqs.
     */
    private int findIndexOfMax()
    {
        int value = myFreqs.get(0);
        int maxIndex = 0;

        for (int k = 0; k < myFreqs.size(); k++)
        {
            if (myFreqs.get(k) > value)
            {
                value = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args)
    {
        WordFrequencies wordFreqs = new WordFrequencies();
        wordFreqs.tester();
    }

}
