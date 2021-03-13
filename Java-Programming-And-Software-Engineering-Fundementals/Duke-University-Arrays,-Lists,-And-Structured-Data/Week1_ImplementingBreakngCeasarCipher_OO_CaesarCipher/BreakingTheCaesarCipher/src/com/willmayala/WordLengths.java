package com.willmayala;

import edu.duke.*;
import java.util.*;

/**
 * Program to figure out the length ofg the most common word of words from a file.
 *
 * @author toussaintwill Allos111
 * @version October 2019
 */

public class WordLengths
{
    /**
     * This method should read in the words from FileResource resource and count
     * the number of the words of each length for all the words in resource,
     * storing these counts in the array counts.
     *
     * @param resource file containing the words
     * @param counts   stores all the counts
     */
    private void countWordLengths(FileResource resource, int[] counts)
    {
        for (String wordInFile : resource.words())
        {
            wordInFile = wordInFile.toLowerCase();
            int countWordOfEachLength = 0;
            int largestSizeSoFar = 0;

            for (int k = 0; k < wordInFile.length(); k++) {
                if ((k == 0 || k == (wordInFile.length() - 1)) &&
                        !Character.isLetter(wordInFile.charAt(k))) {
                    continue;
                }
                countWordOfEachLength++;
            }
            if (wordInFile.length() >= (counts.length)) {
                largestSizeSoFar = counts.length - 1;
            }
            if (wordInFile.length() > 0) {
                counts[largestSizeSoFar]++;
            }
            counts[countWordOfEachLength]++;
            //counts[largestSizeSoFar]++;
        }
    }

    /**
     * Testing countWordLengths() and indexOf(int[] values)
     */
    private void testCountWordLengths()
    {
        FileResource resource = new FileResource();
        int[] counts = new int[31];

        countWordLengths(resource, counts);

        for (int k = 0; k < counts.length; k++) {
            System.out.println(counts[k] + " word of length " + k);
        }
        System.out.println("The most common word is of the length of " + indexOfMax(counts));
    }

    /**
     * This method returns the index position of the largest element in values.
     * Then add code to the method testCountWordLengthsEssay3 to call indexOfMax
     * to determine the most common word length in the file. For example, calling
     * indexOfMax after calling countWordLengthsEssay3 on the file smallHamlet.txt
     * should return 3
     *
     * @param values array of integers containing the lengths of the common words
     */
    private int indexOfMax(int[] values)
    {
        int indexOfMax = 0;
        for (int index = 0; index < values.length; index++)
        {
            if (values[index] > values[indexOfMax]) {
                indexOfMax = index;
            }
        }
        return indexOfMax;
    }

    public static void main(String[] args)
    {
        WordLengths wordLength = new WordLengths();
        wordLength.testCountWordLengths();
    }
}
