/**
 * This program aims to figure out the most common words used in the English language
 * from any given file.
 */

import edu.duke.*;
import java.util.*;
import java.io.*;

public class CommonWords
{
    private String[] getCommonWords()
    {
        FileResource file = new FileResource("data/3000_commonWords.txt");
        String[] commonWords = new String[3000];
        int indexOfCommonWord = 0;

        for (String wordInFile : file.words())
        {
            commonWords[indexOfCommonWord] = wordInFile;
            indexOfCommonWord ++;
        }
        return commonWords;
    }

    private int indexOf(String[] list, String word)
    {
        for (int k = 0; k < list.length ; k++)
        {
            if (list[k].equals(word))
            {
                return k;
            }
        }
        return -1;
    }

    private void countCommonWords(FileResource resource, String[] common, int[] counts)
    {
        for (String wordInFile : resource.words())
        {
            wordInFile = wordInFile.toLowerCase();
            int indexOfCommonWord = indexOf(common, wordInFile);

            if (indexOfCommonWord != -1)
            {
                counts[indexOfCommonWord]++;
            }
        }
    }

    private void countCommonWords()
    {
        String[] plays = {"smallHamlet.txt"};
        String[] commonWords = getCommonWords();
        int[] counts = new int[commonWords.length];

        for (String play : plays)
        {
            FileResource resource = new FileResource("data/" + play);
            countCommonWords(resource, commonWords, counts);
            System.out.println("done with " + play);
        }

        for (int k = 0; k < commonWords.length; k++)
        {
            System.out.println("The word" + "" + commonWords[k] + "" + " appeears "
            + "\t " + counts[k] + " times");
        }
//        String[] lines = new String[];
//        String[] data = new String[lines.size];
//        BufferedReader fileReader = new BufferedReader(new FileReader());
//        List <String> lines = new ArrayList <String>();
//
//        while ((String String line = fileReader.readLine();) != null)
//        {
//            lines.add(line);
//            System.out.println(data);
//        }
     }

     public static void main(String[] args)
     {

     }
}
